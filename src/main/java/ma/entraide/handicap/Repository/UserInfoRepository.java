package ma.entraide.handicap.Repository;


import ma.entraide.handicap.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> ,
        PagingAndSortingRepository<UserInfo, Integer> {

    Optional<UserInfo> findByName(String name);

    Optional<UserInfo> findByEmail(String email);



}
