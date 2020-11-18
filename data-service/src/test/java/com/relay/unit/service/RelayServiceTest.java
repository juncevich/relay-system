package com.relay.unit.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class RelayServiceTest {

//    @Mock
//    private RelayRepository relayRepository;
//
//    @InjectMocks
//    private RelayService relayService;
//
//
//    @Disabled
//    @Test
//    public void findRelayByDate() {
//        Relay relay = new Relay();
//        relay.setId(BigInteger.valueOf(1));
//        relay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6));
////        when(relayRepository.findByDateOfManufacture(any(), any()))
////                .thenReturn(new PageImpl<>(Lists.newArrayList(relay)));
//
//
//        List<Relay> foundedRelayList = relayService
//                .findByDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6)).getContent();
//
//        assertEquals(relay.getId(), foundedRelayList.get(0).getId());
//        assertEquals(relay.getDateOfManufacture(), foundedRelayList.get(0).getDateOfManufacture());
//    }
//
//    @Disabled
//    @Test
//    public void testFindRelayAfterDateOfManufacture() {
//
//        Relay equalsRelay = new Relay();
//        equalsRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 5));
//        relayService.save(equalsRelay);
//
//        Relay lessRelay = new Relay();
//        lessRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6));
//        relayService.save(lessRelay);
//
//        Relay moreRelay = new Relay();
//        moreRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 7));
//        Relay savedMoreRelay = relayService.save(moreRelay);
//
//        List<Relay> foundedRelayList = relayService
//                .findByDateOfManufactureAfter(LocalDate.of(2018, Month.JUNE, 6)).getContent();
//        assertEquals(1, foundedRelayList.size());
//        assertEquals(savedMoreRelay.getId(), foundedRelayList.get(0).getId());
//
//    }
//
//    @Disabled
//    @Test
//    public void testFindRelayBeforeDateOfManufacture() {
//
//        Relay equalsRelay = new Relay();
//        equalsRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 5));
//        relayService.save(equalsRelay);
//
//        Relay lessRelay = new Relay();
//        lessRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 6));
//        relayService.save(lessRelay);
//
//        Relay moreRelay = new Relay();
//        moreRelay.setDateOfManufacture(LocalDate.of(2018, Month.JUNE, 7));
//        relayService.save(moreRelay);
//
//        List<Relay> foundedRelayList = relayService
//                .findByDateOfManufactureBefore(LocalDate.of(2018, Month.JUNE, 6)).getContent();
//        assertEquals(1, foundedRelayList.size());
//        assertEquals(equalsRelay.getId(), foundedRelayList.get(0).getId());
//
//    }
//
//    @Test
//    public void findAll() {
//
//        RelayEntity relay1 = RelayEntity.builder().serialNumber("012345").build();
//        RelayEntity relay2 = RelayEntity.builder().serialNumber("012345").build();
//        RelayEntity relay3 = RelayEntity.builder().serialNumber("012345").build();
//        RelayEntity relay4 = RelayEntity.builder().serialNumber("012345").build();
//        List<RelayEntity> relays = Lists.newArrayList(relay1, relay2, relay3, relay4);
//        when(relayRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(relays));
//
//        List<Relay> relayList = relayService.findAll(PageRequest.of(0, 10));
//        assertNotNull(relayList);
//        assertEquals(4, relayList.size());
//
//    }
//
//    @Test
//    public void findRelayByStationName() {
//        Station           monetnajaStation         = Station.builder().build();
//        List<RelayEntity> relaysByMonetnajaStation = createRelaysByStation(4, monetnajaStation);
//
//        when(relayRepository.findRelaysByStationName("Монетная")).thenReturn(relaysByMonetnajaStation);
//
//        List<Relay> relays = relayService.findByStationName("Монетная");
//        assertNotNull(relays);
//        assertEquals(4, relays.size());
//    }


}
