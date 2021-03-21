package study.h2.domain.member.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.h2.domain.member.dto.MemberJoinRequest;
import study.h2.domain.member.dto.MemberUpdateRequest;
import study.h2.domain.member.entity.Member;
import study.h2.domain.member.dao.MemberRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member addMember(MemberJoinRequest dto) {
        return memberRepository.save(dto.toEntity());
    }

    public List<Member> findAll() {
        return null;
    }

    public Member findById(Long id) {
        return null;
    }

    public Member updateById(Long id, MemberUpdateRequest dto) {
        return null;
    }

    public void deleteById(Long id) {

    }
}
