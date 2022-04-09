package study.datajpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import java.util.Arrays;
import java.util.List;

@Transactional
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    void testMember() {
        Member member = new Member("userA");

        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        Assertions.assertEquals(savedMember.getId(), findMember.getId());
        Assertions.assertEquals(savedMember.getUsername(), findMember.getUsername());
        Assertions.assertEquals(savedMember, findMember);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        Assertions.assertEquals(findMember1, member1);
        Assertions.assertEquals(findMember2, member2);

        List<Member> all = memberRepository.findAll();
        Assertions.assertEquals(all.size(), 2);

        memberRepository.delete(member1);
        memberRepository.delete(member2);

        Assertions.assertEquals(memberRepository.count(), 0);
    }

    @Test
    public void findByUsernameAndAgeGreaterThan() {
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("AAA", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        Assertions.assertEquals(result.get(0).getUsername(), "AAA");
        Assertions.assertEquals(result.get(0).getAge(), 20);
        Assertions.assertEquals(result.size(), 1);
    }

    @Test
    public void testQuery() {
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findUser("AAA", 10);
        Assertions.assertEquals(result.get(0), member1);

    }

    @Test
    public void testUsernameQuery() {
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<String> result = memberRepository.findUsernameList();
        Assertions.assertEquals(result.size(), 2);
    }

    @Test
    public void testMemberDtoQuery() {
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);
        member1.setTeam(team);
        member2.setTeam(team);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<MemberDto> result = memberRepository.findMemberDto();
        Assertions.assertEquals(result.size(), 2);
    }

    @Test
    public void testNamesQuery() {
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        Assertions.assertEquals(result.size(), 2);
    }
}