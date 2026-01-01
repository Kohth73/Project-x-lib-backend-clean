package libm.backend.lib.service;

import libm.backend.lib.entity.Member;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    Member createMember(Member member);

    Member updateMember(Long id, Member member);

    Optional<Member> getMemberById(Long id);

    List<Member> getAllMembers();

    void deleteMember(Long id);
}


