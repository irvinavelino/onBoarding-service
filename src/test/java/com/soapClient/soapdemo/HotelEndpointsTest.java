package com.soapClient.soapdemo;

import com.soapClient.soapdemo.entity.HotelEntity;
import com.soapClient.soapdemo.repository.HotelEntityRepository;
import com.soapClient.soapdemo.service.HotelEntityService;
import com.soapClient.soapdemo.service.HotelEntityServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.webservices.server.WebServiceServerTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class HotelEndpointsTest {
	@Mock
	private HotelEntityRepository hotelEntityRepository;

	@InjectMocks
	private HotelEntityServiceImpl underTest;

	static HotelEntity testHotel = new HotelEntity();

	@BeforeEach
	public void init() throws Exception {
		testHotel.setHotelId(100);
		testHotel.setName("Hotel test 1");
		testHotel.setAddress("Test address 1");
		testHotel.setRating(3);
		testHotel.setAmenities(Collections.singletonList("Restaurant, Pool, Beach, Bar"));
	}
	@Test
	void createHotel() {
		doReturn(testHotel).when(hotelEntityRepository).save(testHotel);
		HotelEntity resultHotel = underTest.addEntity(testHotel);
		assertEquals(testHotel, resultHotel);
		System.out.println(resultHotel.getAmenities());
	}
}