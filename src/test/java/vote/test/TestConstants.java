package vote.test;

import vote.domain.studymeeting.Summary;
import vote.domain.studymeeting.Title;
import vote.domain.user.IpAddress;
import vote.domain.user.User;

public class TestConstants {

    public static final IpAddress IP_ADDRESS1 = new IpAddress("xxx");
    public static final IpAddress IP_ADDRESS2 = new IpAddress("yyy");
    public static final IpAddress IP_ADDRESS3 = new IpAddress("zzz");
    public static final User USER1 = new User(IP_ADDRESS1);
    public static final User USER2 = new User(IP_ADDRESS2);
    public static final User USER3 = new User(IP_ADDRESS3);

    public static final Title TITLE1 = new Title("aaa");
    public static final Title TITLE2 = new Title("bbb");
    public static final Title TITLE3 = new Title("ccc");
    public static final Summary SUMMARY1 = new Summary("AAA");
    public static final Summary SUMMARY2 = new Summary("BBB");
    public static final Summary SUMMARY3 = new Summary("CCC");
}
