# JSP

## 목차

1. [JSP와 서블릿](#01-jsp와-서블릿)
2. [웹 동작 구조 (WAS)](#02-웹-동작-구조-was)
3. [요청 방식과 응답 방식](#03-요청-방식과-응답-방식)
4. [서블릿 기초 실습](#04-서블릿-기초-실습)
5. [MVC 패턴](#05-mvc-패턴)
6. [DBCP와 JNDI](#06-dbcp와-jndi)
7. [MyBatis](#07-mybatis)

---

=======
## 01. JSP와 서블릿

> 전제: **서블릿은 JAVA, JSP는 HTML.** JSP는 조회 따위, 서블릿은 처리 따위를 맡는다.

### JSP (Java Server Page)

HTML을 중심으로 자바와 연동하여 사용하는 웹 언어. **HTML 코드 안에 JAVA 코드**를 작성할 수 있다. (= HTML 중심)

### 서블릿 (Servlet)

**JAVA 코드 안에 HTML 코드**를 작성할 수 있는 JAVA 프로그램. (= JAVA 중심)

- 자바 파일이 `extends HttpServlet` 하면 서블릿이 된다.
- Thread에 의해 서블릿의 `service()` 메소드가 호출되고, 전송 방식에 따라 `doGet()` 또는 `doPost()`가 호출된다.
- `web.xml`은 요청을 알맞은 서블릿으로 안내하는 **이정표** 역할을 한다.

---

## 02. 웹 동작 구조 (WAS)

### 서버

사용자의 요청에 맞는 서비스를 제공해주는 것.

- **요청 (Request)**: 클라이언트 → 서버
- **응답 (Response)**: 서버 → 클라이언트

### 처리 흐름

| 구성 요소 | 역할 |
|-----------|------|
| **웹 서버 (Apache)** | 요청이 정적/동적 데이터인지 판단. 정적이면 미리 준비한 HTML을 그대로 응답, 동적이면 컨테이너로 전달. |
| **웹 컨테이너 (서블릿 컨테이너)** | 동적 데이터를 JSP·서블릿으로 연산·제어하고 DB까지 접근. (DB 접근 같은 복잡한 연산은 JAVA로 처리) |
| **WAS (Tomcat)** | 서블릿을 메모리에 할당(객체화)하고, `web.xml`을 참조해 알맞은 서블릿의 Thread를 생성. 요청·응답 객체를 만들어 전달하고, 연산이 끝나면 메모리에서 해제. |

> **메소드는 누가 호출하나?**
> 내가 직접 호출하는 게 아니라, **톰캣(서블릿 컨테이너)이 HTTP 요청을 보고 자동으로** 적절한 메소드를 실행한다.
> 브라우저 주소창에 URL을 입력하면 GET 요청이므로 `doGet()`이 먼저 실행된다.

---

## 03. 요청 방식과 응답 방식

### 요청 방식 (GET / POST)

- **GET**: 주소(URL)에 데이터를 추가하여 전달. 쿼리 스트링(`?key=value`)에 포함되어 길이 제한이 있고 주소에 노출되어 보안에 취약하다. 상대적으로 **빠르다**.
- **POST**: 데이터를 Header에 별도로 첨부하여 전달. 브라우저 히스토리에 남지 않고 길이 제한이 없으며 보안성이 높다. 상대적으로 **느리다**.
  - → 중요하거나 긴 데이터는 POST로 요청한다.

### 응답 방식 (forward / redirect)

- **forward**: 요청 경로가 그대로 남는다. 단순 페이지 이동이나 응답 페이지에 데이터를 전달할 때 사용. `.jsp` 확장자가 노출되지 않게 할 때도 사용한다. (이전 요청 정보를 기억)
- **redirect**: 요청 경로가 초기화된다(new request). 이전 요청 경로를 보여주고 싶지 않을 때, DB 연산 등 복잡한 작업 후 다른 서블릿으로 요청할 때 사용한다.

```java
// forward — 데이터를 담아 전달 (setAttribute / getAttribute로 꺼냄)
req.setAttribute("키", 값);
req.getRequestDispatcher("/ex02.jsp").forward(req, resp);

// redirect — 경로 초기화
resp.sendRedirect(req.getContextPath() + "/ex02-result");
```

---

## 04. 서블릿 기초 실습

```java
public class Ex02 extends HttpServlet {
    // GET — 입력 화면으로 forward
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) ... {
        req.getRequestDispatcher("/ex02.jsp").forward(req, resp);
    }

    // POST — 파라미터를 받아 연산 후 결과 페이지로 redirect
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) ... {
        int num01 = Integer.parseInt(req.getParameter("number01"));
        int num02 = Integer.parseInt(req.getParameter("number02"));
        int result = num01 + num02;
        resp.sendRedirect(req.getContextPath() + "/ex02-result?result=" + result);
    }
}
```

- `req.getParameter("name")`으로 폼 입력값을 받는다.
- 실습: 정수 2개 덧셈, 과일 이름·가격 출력, 지역명 분기 출력, 로그인 검사(성공 시 환영 / 실패 시 로그인 화면으로 복귀).

---

## 05. MVC 패턴

### JSP only / Model1 / Model2

| 방식 | 구조 | 특징 |
|------|------|------|
| **JSP only** | `a.jsp → b.jsp → c.jsp` (DB 코드까지 jsp 안에) | 분리되어 있지 않아 소규모에 적합. 확장될수록 가독성·유지보수↓ |
| **Model1** | JSP + `DAO.java` | JSP 내 자바 코드는 줄지만 Controller와 View가 섞임 |
| **Model2** | `a.jsp → web.xml → FrontController → Controller → DAO → DB` | 비즈니스 로직을 완벽하게 분리. 유지보수·재사용↑ |

### Model2 동작 흐름

1. 사용자 정의 확장자(`*.member`)로 요청하면 `web.xml`이 그룹화된 요청을 하나의 **FrontController**로 보낸다.
2. FrontController는 요청명(`.member` 앞)으로 어떤 로직을 수행할지 **분기 처리**한다.
3. 요청별 **Controller**가 `req`, `resp`를 받아 처리한다. 모든 Controller가 같은 메소드를 갖도록 **인터페이스(`Action`)로 추상화**한다.
4. DB 연산은 **DAO**로 분리한다.
5. 처리 후 "이동 여부·방식·경로"를 담은 **`Result`** 객체를 리턴하면, FrontController가 그에 맞게 forward/redirect 한다.

```java
// Action — 모든 Controller가 구현하는 인터페이스
public interface Action {
    Result execute(HttpServletRequest req, HttpServletResponse resp) ...;
}

// Result — 이동 방식(redirect/forward)과 경로를 담는다
public class Result {
    private boolean isRedirect;  // true면 redirect, false면 forward
    private String path;
}

// FrontController — 요청명으로 분기하고, Result에 따라 이동
String target = uri.substring(0, uri.lastIndexOf(".")).replace(req.getContextPath(), "");
if (target.equals("/join-ok")) result = new JoinOkController().execute(req, resp);
...
if (result.isRedirect()) resp.sendRedirect(result.getPath());
else req.getRequestDispatcher(result.getPath()).forward(req, resp);
```

> `b-mvc-review`, `c-mvc` 프로젝트에서 회원가입/로그인을 Model2 + MyBatis로 구현했다.

---

## 06. DBCP와 JNDI

### DBCP (Database Connection Pool)

사용자 요청마다 DB 연결을 새로 맺으면 요청이 많을 때 속도가 저하된다.
→ 미리 Connection 객체를 여러 개 만들어 두고, 필요할 때 활성화해서 가져다 쓴 후 반환하는 기법이다.
(JDBC에서 커넥션을 매번 열고 닫는 비효율을 개선)

### JNDI (Java Naming Directory Interface)

디렉터리 서비스에서 제공하는 자바 기본 API로, **외부에 있는 객체를 가져오기 위한 기술**이다.
외부 파일에 작성된 객체를 자바에서 점(`.`)을 찍어 편하게 사용할 수 있게 해 준다.

---

## 07. MyBatis

소스코드 안에 SQL문을 작성하면 코드가 길어지고 섞여 유지보수가 어렵다.
MyBatis는 **SQL문을 XML 파일에 분리**하여 자바 코드가 깔끔해지고 SQL 수정이 편해진다.

### 동작 흐름

`MyBatisConfig`에서 설정을 읽어 `SqlSessionFactory`를 만들고, DAO에서 `SqlSession`을 열어 CRUD를 실행한다.

```java
// MyBatisConfig — 팩토리를 한 번만 생성 (static)
static {
    Reader reader = Resources.getResourceAsReader("com/app/mybatis/config/config.xml");
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
}

// DAO — SqlSession으로 CRUD 실행 ("namespace.id"로 매퍼 XML의 SQL 호출)
public MemberDAO() {
    sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);  // true = 자동 커밋
}
public void insert(MemberVO memberVO) {
    sqlSession.insert("member.insert", memberVO);
}
public Optional<MemberVO> selectByMemberEmailAndMemberPassword(MemberVO memberVO) {
    return Optional.ofNullable(
        (MemberVO) sqlSession.selectOne("member.selectByMemberEmailAndMemberPassword", memberVO));
}
```

- **VO (Value Object)**: 테이블 구조를 그대로 담는 모델 객체(`MemberVO`). `equals()`·`hashCode()`를 재정의해 사용.
- `config.xml`은 JNDI로 데이터소스를 가져와 `SqlSessionFactory`를 구성하고, 매퍼 XML에 실제 SQL문을 작성한다.
