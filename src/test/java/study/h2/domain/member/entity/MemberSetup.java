package study.h2.domain.member.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MemberSetup {

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

    private Member buildMember(String name) {
        return Member.builder()
            .name(name)
            .build();
    }

}
