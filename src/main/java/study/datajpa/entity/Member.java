package study.datajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Service
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    protected Member() {

    }

    public Member(String username) {
        this.username = username;
    }
}
