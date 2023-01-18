package com.soapClient.soapdemo;

import com.onboarding.hotels.Amenities;
import com.soapClient.soapdemo.entity.HotelEntity;
import com.soapClient.soapdemo.exceptions.HotelNotFoundException;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class HotelEndpointsTest {
	@Mock
	private HotelEntityRepository hotelEntityRepository;

	@InjectMocks
	private HotelEntityServiceImpl underTest;

	static HotelEntity testHotel = new HotelEntity();
	static Amenities testAmenity=new Amenities();

	@BeforeEach
	public void init() throws Exception {
		testHotel.setHotelId(10);
		testHotel.setName("Hotel test 1");
		testHotel.setAddress("Test address 1");
		testHotel.setRating(3);
	}
	@Test
	void createHotel() {
		doReturn(testHotel).when(hotelEntityRepository).save(testHotel);
		HotelEntity resultHotel = underTest.addEntity(testHotel);
		assertEquals(testHotel, resultHotel);
		//System.out.println(resultHotel.getAmenities());
	}
	@Test
	void editHotel() {
		doReturn(Optional.of(testHotel)).when(hotelEntityRepository).findByHotelId(10);
		underTest.updateEntity(testHotel);
		verify(hotelEntityRepository).findByHotelId(10);
		verify(hotelEntityRepository).save(testHotel);
	}

	@Test
	void editHotelNotFound() throws HotelNotFoundException {
		Assertions.assertThrows(HotelNotFoundException.class,()->{
			doReturn(Optional.empty()).when(hotelEntityRepository).findByHotelId(200);
			underTest.updateEntity(testHotel);
		});
	}

	@Test
	void getAllHotels(){
		List<HotelEntity> result = (List<HotelEntity>) underTest.getAllEntities();
		verify(hotelEntityRepository).findAll();
	}

	@Test
	void filterHotelsByName(){
		List<HotelEntity> result = (List<HotelEntity>) underTest.filterEntityByName("test");
		verify(hotelEntityRepository).findByName("test");
	}

	@Test
	void deleteById(){
		doReturn(Optional.of(testHotel)).when(hotelEntityRepository).findByHotelId(10);
		underTest.deleteEntityByHotelId(10);
		verify(hotelEntityRepository).findByHotelId(10);
		verify(hotelEntityRepository).deleteById(10);
	}

	@Test
	void deleteByIdHotelNotFound() throws HotelNotFoundException {
		Assertions.assertThrows(HotelNotFoundException.class,()->{
			doReturn(Optional.empty()).when(hotelEntityRepository).findByHotelId(200);
			underTest.deleteEntityByHotelId(200);
		});
	}
}