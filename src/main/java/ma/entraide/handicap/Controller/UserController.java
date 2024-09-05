package ma.entraide.handicap.Controller;


import ma.entraide.handicap.Entity.AuthRequest;
import ma.entraide.handicap.Entity.RequestBodyObject;
import ma.entraide.handicap.Entity.UserInfo;
import ma.entraide.handicap.Service.JwtService;
import ma.entraide.handicap.Service.LogsConnexionService;
import ma.entraide.handicap.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private LogsConnexionService logsConnexionService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to App !!";
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserInfo userInfo) {
        try {
            String result = userInfoService.addUser(userInfo);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader(value = "X-Forwarded-For", defaultValue = "unknown")String ip
            ,@RequestBody AuthRequest authRequest
    , @RequestHeader(value = "user-agent", defaultValue = "notFound") String device){
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            if(authenticate.isAuthenticated()){
                UserInfo userInfo = userInfoService.findUserByUsername(authRequest.getEmail());
                logsConnexionService.addLogsConnexion(userInfo, ip, device);
                System.out.println(device);
                return ResponseEntity.ok(jwtService.generateToken(authRequest.getEmail()));
            }else {
                throw new UsernameNotFoundException("Invalid user request");
            }
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid credentials");
        }

    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        List<UserInfo> users = userInfoService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getAllUsers(@PathVariable Integer id) {
        try {
            UserInfo user = userInfoService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES')")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id){
        try {
            userInfoService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully!.");
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody UserInfo userInfo){
        try {
            userInfoService.updateUser(id, userInfo);
            return ResponseEntity.ok("User updated successfully!.");
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findCurrentUser(@PathVariable String email) {
        try {
            UserInfo user = userInfoService.findUserByUsername(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            throw new UsernameNotFoundException("Invalid email address");
        }
    }

    @PutMapping("/changepsw/{userId}")

    public ResponseEntity<String> updatePassword(@PathVariable("userId") Integer userId,
                                                 @RequestBody RequestBodyObject passwordUpdate) {

        String oldPassword = passwordUpdate.getOldPassword();
        String newPassword = passwordUpdate.getNewPassword();
        String confirmPassword = passwordUpdate.getConfirmPassword();

        UserInfo user = userInfoService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        boolean passwordUpdated = userInfoService.updatePassword(user, oldPassword, newPassword, confirmPassword);
        if (passwordUpdated) {
            return ResponseEntity.ok("Mot de passe mis à jour avec succès.");
        } else {
            return ResponseEntity.badRequest().body("La mise à jour du mot de passe a échoué. Vérifiez vos informations.");
        }
    }

}
