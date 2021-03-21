package study.h2.domain.member.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import study.h2.domain.member.dao.MemberRepository;

@Component
public class MemberSetup {

    @Autowired
    private MemberRepository memberRepository;

    public Member build(String name) {
        return buildMember(name);
    }

    public Member buildOneAuto() {
        return buildMember("member0");
    }

    public List<Member> buildListAuto(Integer count) {
        List<Member> memberList = new ArrayList<>();
        IntStream.range(0, count).forEach(value -> {
            memberList.add(buildMember(String.format("member%d", value)));
        });
        return memberList;
    }

    public Member saveOne() {
        return memberRepository.save(buildOneAuto());
    }

    public List<Member> saveList(Integer count) {
        return memberRepository.saveAll(buildListAuto(count));
    }

    private Member buildMember(String name) {
        return Member.builder()
            .name(name)
            .build();
    }

}
