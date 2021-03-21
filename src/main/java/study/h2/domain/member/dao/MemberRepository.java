package study.h2.domain.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import study.h2.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
