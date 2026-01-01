package libm.backend.lib.service.service.impl;

import libm.backend.lib.entity.Member;
import libm.backend.lib.repository.MemberRepository;
import libm.backend.lib.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Long id, Member member) {
        Member existing = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        existing.setName(member.getName());
        existing.setAddress(member.getAddress());
        existing.setPhone(member.getPhone());
        existing.setEmail(member.getEmail());
        existing.setMembershipDate(member.getMembershipDate());
        existing.setUserId(member.getUserId());

        return memberRepository.save(existing);
    }

    @Override
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}



