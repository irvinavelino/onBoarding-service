package com.soapClient.soapdemo.endpoint;

import com.onboarding.hotels.*;
import com.soapClient.soapdemo.entity.HotelEntity;
import com.soapClient.soapdemo.service.HotelEntityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class HotelEndpoints
{
    public static final String NAMESPACE_URI ="http://onBoarding.com/hotels";
    private HotelEntityService service;
    public HotelEndpoints() {

    }
    @Autowired
    public HotelEndpoints(HotelEntityService service) {
        this.service = service;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetHotelByIdRequest")
    @ResponsePayload
    public GetHotelByIdResponse getHotelById(@RequestPayload GetHotelByIdRequest request) {
        GetHotelByIdResponse response = new GetHotelByIdResponse();
        HotelEntity hotelEntity = service.getEntityByHotelId(request.getHotelId());
        HotelDetails hotelDetails = new HotelDetails();
        BeanUtils.copyProperties(hotelEntity, hotelDetails);
        response.setHotelDetails(hotelDetails);
        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllHotelsRequest")
    @ResponsePayload
    public GetAllHotelsResponse getAllHotels(@RequestPayload GetAllHotelsRequest request) {
        GetAllHotelsResponse response = new GetAllHotelsResponse();
        List<HotelDetails> hotelDetailsList = new ArrayList<HotelDetails>();
        List<HotelEntity> hotelEntityList = service.getAllEntities();
        for (HotelEntity entity : hotelEntityList) {
            HotelDetails hotelDetails = new HotelDetails();
            BeanUtils.copyProperties(entity, hotelDetails);
            hotelDetailsList.add(hotelDetails);
        }
        response.getHotelDetails().addAll(hotelDetailsList);

        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addHotelRequest")
    @ResponsePayload
    public AddHotelResponse addHotel(@RequestPayload AddHotelRequest request) {
        AddHotelResponse response = new AddHotelResponse();
        HotelDetails newHotelDetails = new HotelDetails();
        ServiceStatus serviceStatus = new ServiceStatus();

        HotelEntity newHotelEntity = new HotelEntity(request.getName(), request.getAddress(),request.getRating(),request.getAmenity());
        HotelEntity savedHotelEntity = service.addEntity(newHotelEntity);

        if (savedHotelEntity == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding Entity");
        } else {

            BeanUtils.copyProperties(savedHotelEntity, newHotelDetails);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
        }

        response.setHotelDetails(newHotelDetails);
        response.setServiceStatus(serviceStatus);
        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateHotelRequest")
    @ResponsePayload
    public UpdateHotelResponse updateHotel(@RequestPayload UpdateHotelRequest request) {
        UpdateHotelResponse response = new UpdateHotelResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        HotelEntity hotelFromDB = service.getEntityByHotelId(request.getHotelDetails().getHotelId());
        if(request.getHotelDetails().getName()==null)
        {
            request.getHotelDetails().setName(hotelFromDB.getName());
        }
        else if (request.getHotelDetails().getAddress()==null)
        {
            request.getHotelDetails().setAddress(hotelFromDB.getAddress());
        }
        else if (request.getHotelDetails().getAmenities()==null)
        {
            request.getHotelDetails().setAmenities(hotelFromDB.getAmenities());
        }
        if(hotelFromDB == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("Hotel = " + request.getHotelDetails().getHotelId() + " not found");
        }else {

            hotelFromDB.setName(request.getHotelDetails().getName());
            hotelFromDB.setAddress(request.getHotelDetails().getAddress());
            hotelFromDB.setRating(request.getHotelDetails().getRating());
            hotelFromDB.setAmenities(request.getHotelDetails().getAmenities());
            // 3. update the movie in database

            boolean flag = service.updateEntity(hotelFromDB);

            if(flag == false) {
                serviceStatus.setStatusCode("CONFLICT");
                serviceStatus.setMessage("Exception while updating Entity=" + request.getHotelDetails().getName());;
            }else {
                serviceStatus.setStatusCode("SUCCESS");
                serviceStatus.setMessage("Content updated Successfully");
            }
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteHotelByIdRequest")
    @ResponsePayload
    public DeleteHotelByIdResponse deleteHotel(@RequestPayload DeleteHotelByIdRequest request) {
        DeleteHotelByIdResponse response = new DeleteHotelByIdResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        boolean flag = service.deleteEntityByHotelId(request.getHotelId());

        if (flag == false) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Exception while deleting Entity id=" + request.getHotelId());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

}
