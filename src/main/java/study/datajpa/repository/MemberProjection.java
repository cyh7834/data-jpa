package study.datajpa.repository;

// 네이티브 쿼리와 projection을 활용
public interface MemberProjection {
    Long getId();
    String getUsername();
    String getTeamName();
}
