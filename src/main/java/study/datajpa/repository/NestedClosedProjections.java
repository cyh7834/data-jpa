package study.datajpa.repository;

// 중첩 구조로 조회 시 사용. 루트(username)은 하나만 가져올 수 있는데 team은 정보를 다 가져오게됨. 최적화가 부족함.
public interface NestedClosedProjections {
    String getUsername();
    TeamInfo getTeam();

    interface TeamInfo {
        String getName();
    }
}
