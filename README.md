# [김영한님] 실전! 스프링 데이터 JPA
기존에 EntityManager를 직접 주입받아서 CRUD를 처리하던 방식에서 Spring Data JPA를 사용한 방식으로 변경한다.

## 배운점
- Spring Data JPA 기반 Repository
JpaRepository를 상속받아 interface를 생성하면 구현 클래스를 Spring Data JPA가 생성한다. 컴포넌트 스캔도 자동으로 처리하고, JPA 예외를 스프링의 예외로 변환하는 과정도 자동으로 처리한다.

- Pageable과 Sort를 파라미터로 받아 페이징과 정렬을 처리하는 방법
- 벌크성 수정, 삭제 쿼리 방법
벌크 연산은 영속성 컨텍스트를 무시하고 실행하기 때문에, 영속성 컨텍스트에 있는 엔티티의 값과 DB의 값이 달라질 수 있다.
@Modifying(clearAutomatically = true) 어노테이션을 사용하여 JPQL 실행 후에 자동으로 영속성 컨텍스트를 비운뒤에 다시 최신으로 유지해야한다.

- Spring Data Auditing 적용
스프링 부트 설정 클래스에 @EnableJpaAuditing 어노테이션을 적용하고, Entity에 @EntityListeners(AuditingEntityListener.class) 어노테이션을 적용한 뒤에 
@CreatedDate, @LastModifiedDate, @CreatedBy, @LastModifiedBy 어노테이션을 사용하여 등록일, 수정일, 등록자, 수정자를 자동으로 저장할 수 있다. 유지보수 관점에서 편리하다.
