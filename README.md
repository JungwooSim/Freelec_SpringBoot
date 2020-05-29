## "스프링 부트와 AWS로 혼자 구현하는 웹서비스" 책을 기반으로한 학습

#### grade
- repositoies : 각종 의존성들을 어떤 원격 저장소에서 받을지 지정하는 곳
  - 보통 mavenCentral을 많이 사용하지만, 라이브러리 업로드에 난이도 문제때문에 최근에는 jcenter도 많이 사용 (두개 동시에 사용가능)

#### TDD
실무에서 깨달은 TDD의 장점
- 빠른 피드백(컴파일 과정 간소화)
- 자동 검증(눈으로 확인할 필요 없음)
- 개발자가 생성한 기능을 안전하게 보호(프로젝트 단위가 커질수록 서비스들의 의존성 또한 높아지게 되므로 장애포인트가 더 늘어날 수 있다.)

xUnit : 테스트 코드 작성을 도와주는 프레임워크 (x 는 개발환경을 뜻함)<br>
JUnit4 버전을 사용할 계획. (JUnit5버전도 있지만, 아직은 많은 회사에서 4버전을 사용하므로)<br>

#### Spring Data JPA

- JPA는 인터페이스로서 자바 표준명세서 이다.
    - 구현체로는 대표적으로 Hibernate, EclipseLink 등이 있다.

**@Setter을 무작정 생성하는 것은 바람직하지 않다.**

- 무작정 생성하게 되면 클래스의 인스턴스 값들이 언제 어디서 변환되는지 코드상으로 명확히 알기 어렵기 때문에 유지보수할 때 잠재적인 문제가 발생할 수 있다.
- Entity 클래스에는 절대 Setter 메소드를 만들지 않고, 대신 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가 해야 한다.
- **Entity의 기본적인 구조는 생성자를 통해 최종값을 채운 후 DB에 삽입하는 것이다.** 값 변경이 필요하다면 해당 이벤트에 맞는 public 메소드를 생성하는 것이다.

```
// 잘못된 예
public void setStatus(boolean status) {
	this.status = status;
}
--------
// 올바른 예
public void cancelOrder(boolean status) {
	this.status = status;
}
```

### **Spring WEB Layer**
<img src="/img/Spring_web_layer.jpeg" width="400px;">

- Web Layer
    - Controller, JSP/Freemarker 등의 뷰 템플릿 영역
    - Filter, intercepter, controllerAdvice 등 외부 요청과 응답에 대한 전반적인 영역
- Service Layer
    - controller와 Dao의 중간 영역
    - Transaction이 사용되어야 하는 영역
- Repository Layer
    - Databases와 같이 데이터 저장소에 접근하는 영역
    - Data Access Object 영역
- Dtos - Data Transfer Object
    - 계층 간에 데이터 교환을 위한 객체
    - 예를 들어, 뷰 템플릿 엔진에서 사용될 객체나 Repository Layer에서 결과로 넘겨준 객체 등
- Domain Model
    - 도메인이라 불리는 개발 대상을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화 시킨 것을 의미
    - @Entity가 사용되는 영역이라고 이해해도 된다. 하지만, 무조건 데이터베이스의 테이블과 관계가 있어야만 그런것은 아니다.

**Dirty Checking**

- JPA의 개념에서 나온 말이며 Entity 객체의 값을 변경하여 쿼리문 질의 없이 적용하는 것을 말함.

**Spring Data - Auditing**

- Spring Dtata에서는 Entity를 생성 또는 변경한 시점을 추적할 수 있는 기능 제공해준다.
