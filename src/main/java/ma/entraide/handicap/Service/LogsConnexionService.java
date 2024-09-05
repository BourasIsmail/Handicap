package ma.entraide.handicap.Service;

import jakarta.servlet.http.HttpServletRequest;
import ma.entraide.handicap.Entity.LogsConnexion;
import ma.entraide.handicap.Entity.UserInfo;
import ma.entraide.handicap.Repository.LogsConnexionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Service
public class LogsConnexionService {

    @Autowired
    private LogsConnexionRepo logsConnexionRepo;

    @Autowired
    private UserInfoService userInfoService;

    public String getClientIp(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String clientIp = request.getRemoteAddr();

        return clientIp;
    }

    public LogsConnexion addLogsConnexion(UserInfo user , String ip, String detail) {
        LogsConnexion logsConnexion = new LogsConnexion();

        UserInfo userInfo = userInfoService.getUserById(user.getId());

        logsConnexion.setUser(userInfo);

        logsConnexion.setAccountEmail(userInfo.getEmail());

        logsConnexion.setDateLogin(new Date());

        logsConnexion.setIpAdresse(ip);

        logsConnexion.setDevice(detail);

        return logsConnexionRepo.save(logsConnexion);
    }
}
