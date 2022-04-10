package study.datajpa.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberJpaRepositoryTest {
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    void testMember() {
        Member member = new Member("userA");

        Member savedMember = memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.find(savedMember.getId());

        Assertions.assertEquals(savedMember.getId(), findMember.getId());
        Assertions.assertEquals(savedMember.getUsername(), findMember.getUsername());
        Assertions.assertEquals(savedMember, findMember);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        Member findMember1 = memberJpaRepository.find(member1.getId());
        Member findMember2 = memberJpaRepository.find(member2.getId());

        Assertions.assertEquals(findMember1, member1);
        Assertions.assertEquals(findMember2, member2);

        List<Member> all = memberJpaRepository.findAll();
        Assertions.assertEquals(all.size(), 2);

        memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);

        Assertions.assertEquals(memberJpaRepository.count(), 0);
    }

    @Test
    public void paging() {
        memberJpaRepository.save(new Member("member1", 10));
        memberJpaRepository.save(new Member("member2", 10));
        memberJpaRepository.save(new Member("member3", 10));
        memberJpaRepository.save(new Member("member4", 10));
        memberJpaRepository.save(new Member("member5", 10));

        int age = 10;
        int offset = 0;
        int limit = 3;

        List<Member> members = memberJpaRepository.findByPage(age, offset, limit);
        long totalCount = memberJpaRepository.totalCount(age);

        Assertions.assertEquals(members.size(), 3);
        Assertions.assertEquals(totalCount, 5);
    }

    @Test
    public void bulkUpdate() {
        memberJpaRepository.save(new Member("member1", 10));
        memberJpaRepository.save(new Member("member2", 19));
        memberJpaRepository.save(new Member("member3", 20));
        memberJpaRepository.save(new Member("member4", 21));
        memberJpaRepository.save(new Member("member5", 40));

        int resultCount = memberJpaRepository.bulkAgePlus(20);

        Assertions.assertEquals(resultCount, 3);
    }
}