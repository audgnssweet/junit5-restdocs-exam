package study.h2.domain.member.api;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import study.h2.domain.member.dto.MemberJoinRequest;
import study.h2.domain.member.application.MemberService;
import study.h2.domain.member.dto.MemberResponse;
import study.h2.domain.member.dto.MemberUpdateRequest;
import study.h2.domain.member.dto.MembersResponse;

@RequiredArgsConstructor
@RestController
public class MemberApi {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/members")
    public MemberResponse join(@Valid @RequestBody MemberJoinRequest dto) {
        return new MemberResponse(memberService.addMember(dto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/members")
    public MembersResponse getAll() {
        return new MembersResponse(memberService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/members/{id}")
    public MemberResponse getById(@PathVariable Long id) {
        return new MemberResponse(memberService.findById(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/members/{id}")
    public MemberResponse updateById(@PathVariable Long id,
        @Valid @RequestBody MemberUpdateRequest dto) {
        return new MemberResponse(memberService.updateById(id, dto));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/members/{id}")
    public void deleteById(@PathVariable Long id) {
        memberService.deleteById(id);
    }

}
