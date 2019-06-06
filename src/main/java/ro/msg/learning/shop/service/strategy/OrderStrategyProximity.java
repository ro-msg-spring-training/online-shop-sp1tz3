package ro.msg.learning.shop.service.strategy;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.dto.OrderOutputDTO;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.exception.AddressNotFoundException;
import ro.msg.learning.shop.exception.OrderNotCreatedException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.repository.AddressRepository;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderStrategyProximity implements OrderStrategy{
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<OrderOutputDTO> makeOrderOutputDTO(OrderInputDTO input) throws OrderNotCreatedException {
        Iterable<Location> allLocations = locationRepository.findAll();
        List<OrderOutputDTO> orderOutputDTOS = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String key = "34FAPmsgRFlGTl14E2AIKGq4v1DBSAZa";
        String resourceUrl = "http://www.mapquestapi.com/directions/v2/routematrix?key=" +key;

        Address addressObject = addressRepository.findById(input.getDeliveryAddressId()).orElseThrow(AddressNotFoundException::new);
        String addressString = "\"" + addressObject.getCity()+ ", " + addressObject.getCountry() + "\"";
        Map<Location, BigDecimal> distances = new HashMap<>();

        allLocations.forEach(l -> {
            String locationAddress = " \"" + l.getAddress().getCity() + ", " + l.getAddress().getCountry() + "\"";
            HttpEntity<String> request = new HttpEntity<>("{ locations: [" + addressString + ", " + locationAddress + "]}");
            JsonNode response = restTemplate.postForObject(resourceUrl, request, JsonNode.class);
            BigDecimal distance = response.get("distance").get(1).decimalValue();
            distances.put(l, distance);
        });

        Set<Map.Entry<Location, BigDecimal>> entries = distances.entrySet();
        Comparator<Map.Entry<Location, BigDecimal>> valueComparator = new Comparator<Map.Entry<Location, BigDecimal>>() {
            @Override
            public int compare(Map.Entry<Location, BigDecimal> e1, Map.Entry<Location, BigDecimal> e2) {
                BigDecimal v1 = e1.getValue();
                BigDecimal v2 = e2.getValue();
                return v1.compareTo(v2);
            }
        };
        List<Map.Entry<Location, BigDecimal>> listOfEntries = new ArrayList<>(entries);

        listOfEntries.sort(valueComparator);
        List<Location> sortedLocations = new ArrayList<>();
        listOfEntries.forEach(e->{
            sortedLocations.add(e.getKey());
        });
        List<Integer> products = input.getProductsQuantitiesDTO().getProducts();
        List<Integer> quantities = input.getProductsQuantitiesDTO().getQuantities();
        for(Location l: sortedLocations){
            for(Stock s: l.getStocks()){
                if(products.contains(s.getProduct().getProductId()) && s.getQuantity()>=quantities.get(products.indexOf(s.getProduct().getProductId()))){
                    orderOutputDTOS.add(new OrderOutputDTO(l,
                            productRepository.findById(s.getProduct().getProductId()).orElseThrow(ProductNotFoundException::new)
                            , quantities.get(products.indexOf(s.getProduct().getProductId()))));
                    quantities.remove(quantities.get(products.indexOf(s.getProduct().getProductId())));
                    products.remove(s.getProduct().getProductId());

                }
            }
        }
        orderOutputDTOS.forEach(o->System.out.println(o));
        return orderOutputDTOS;
    }
}
