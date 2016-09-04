package vote.infrastructure.user;

import vote.domain.user.IpAddress;
import vote.domain.user.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
public class CurrentAccessUser {
    @Inject
    private HttpServletRequest request;

    public User get() {
        String addr = this.request.getRemoteAddr();
        IpAddress ipAddress = new IpAddress(addr);
        return new User(ipAddress);
    }
}
