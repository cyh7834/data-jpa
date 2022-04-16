package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.datajpa.entity.Item;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void save() {
        // id 값이 처음엔 없으니까 jpa 구현체 내부에서 persist로 실행됨.
        // @GeneratedValue를 사용하지 않고 커스텀한 id를 사용한다면 id 값이 먼저 셋팅이 되고
        // 저장 로직이 실행되기 때문에 jpa 내부에서는 persist가 아닌 기존에 있던 데이터로 인식하여 merge 로직이 수행되면서
        // select 쿼리가 발생된다. Persistable로 persist 임을 계산해야함.
        itemRepository.save(new Item("A"));
    }

}