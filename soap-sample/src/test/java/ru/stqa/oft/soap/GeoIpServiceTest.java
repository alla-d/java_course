package ru.stqa.oft.soap;

public class GeoIpServiceTest {
  @Test
  public void testMyIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("77.246.237.162");
    assertEquals(geoIP.getCountryCode(), "RUS");
  }

  @Test
  public void testInvalidIp() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("77.246.237.xxx");
    assertEquals(geoIP.getCountryCode(), "RUS");
  }
}
