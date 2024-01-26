package cydeo.service.impl;


import cydeo.client.CountryClient;
import cydeo.client.WeatherApiClient;
import cydeo.dto.AddressDTO;
import cydeo.dto.weather.WeatherDTO;
import cydeo.entity.Address;
import cydeo.repository.AddressRepository;
import cydeo.service.AddressService;
import cydeo.util.MapperUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;
   private final WeatherApiClient weatherApiClient;
   private final CountryClient countryClient;

    @Value("${access_key}") //@Value to access my access_key inside application property
    private String access_key;

    public AddressServiceImpl(AddressRepository addressRepository, MapperUtil mapperUtil, WeatherApiClient weatherApiClient, CountryClient countryClient) {
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
        this.weatherApiClient = weatherApiClient;
        this.countryClient = countryClient;
    }




    @Override
    public List<AddressDTO> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(address -> mapperUtil.convert(address, new AddressDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO findById(Long id) throws Exception {

        Address foundAddress = addressRepository.findById(id)
                .orElseThrow(() -> new Exception("No Address Found!"));

        AddressDTO addressDTO = mapperUtil.convert(foundAddress, new AddressDTO());
        //we will get the current temperature and set based on city, return dto
        addressDTO.setCurrentTemperature(getCurrentWeather(addressDTO.getCity()).getCurrent().getTemperature());
        //we will get the flag link based on the country provided then return dto
        addressDTO.setFlag(retrieveCountryById(addressDTO.getCountry()));
        return addressDTO;
    }

    private String retrieveCountryById(String country) { // api method that is returning( list get(0))
        return countryClient.getCountryInfo(country).get(0).getFlags().getPng();
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) throws Exception {

        addressRepository.findById(addressDTO.getId())
                .orElseThrow(() -> new Exception("No Address Found!"));

        Address addressToSave = mapperUtil.convert(addressDTO, new Address());

        addressRepository.save(addressToSave);
        AddressDTO updateAddress = mapperUtil.convert(addressToSave, new AddressDTO());
     // updateAddress.setCurrentTemperature(getCurrentWeather(updateAddress.getCity()).getCurrent().getTemperature());
     // return updateAddress;
        addressDTO.setCurrentTemperature(retrieveTemperatureByCity(addressDTO.getCity()));

        return addressDTO;

    }

    private Integer retrieveTemperatureByCity(String city) {// api method by Jamal
        return weatherApiClient.getCurrentWeather(access_key, city).getCurrent().getTemperature();
    }

    private WeatherDTO getCurrentWeather(String city){// api method by Ozzy
        return weatherApiClient.getCurrentWeather(access_key, city);
    }

    @Override
    public AddressDTO create(AddressDTO addressDTO) throws Exception {

        Optional<Address> foundAddress = addressRepository.findById(addressDTO.getId());

        if (foundAddress.isPresent()) {
            throw new Exception("Address Already Exists!");
        }

        Address addressToSave = mapperUtil.convert(addressDTO, new Address());

        addressRepository.save(addressToSave);

        return mapperUtil.convert(addressToSave, new AddressDTO());

    }



}