//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//@Service("securityService")
//public class SecurityService {
//
//    public boolean hasRole(Authentication authentication, int role) {
//        if (authentication == null) {
//            return false;
//        }
//        Integer userRole = (Integer) ((MyUserDetails) authentication.getPrincipal()).getRole();
//        return userRole != null && userRole == role;
//    }
//}
