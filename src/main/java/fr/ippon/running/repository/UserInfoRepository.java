package fr.ippon.running.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ippon.running.domain.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	public UserInfo findByLogin(String login);

}
