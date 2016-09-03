package vote.test;

import vote.domain.user.IpAddress;
import vote.domain.user.User;

public class TestConstants {

    public static final IpAddress IP_ADDRESS1 = new IpAddress("xxx");
    public static final IpAddress IP_ADDRESS2 = new IpAddress("yyy");
    public static final IpAddress IP_ADDRESS3 = new IpAddress("zzz");
    public static final User USER1 = new User(IP_ADDRESS1);
    public static final User USER2 = new User(IP_ADDRESS2);
    public static final User USER3 = new User(IP_ADDRESS3);

}
