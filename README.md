Twitter Clone - Java Swing 프로젝트
📚 프로젝트 개요
이 프로젝트는 Java Swing과 MySQL을 사용하여 간단한 Twitter 클론 애플리케이션을 구현한 것입니다. 사용자 인터페이스는 실제 Twitter의 레이아웃을 참고하여 디자인되었으며, 데이터베이스와의 통합을 통해 게시글, 댓글, 좋아요 등의 기능을 제공합니다.

🛠️ 주요 기능
트윗 작성 및 타임라인 표시
사용자 글을 작성하고, 데이터베이스에서 불러온 게시글을 타임라인에 표시합니다.
댓글 관리
게시글에 댓글 작성 및 기존 댓글 보기 기능을 제공합니다.
좋아요 기능
게시글에 좋아요를 추가할 수 있습니다.
MySQL 데이터베이스 연동
데이터베이스에서 사용자 정보, 게시글, 댓글, 좋아요 데이터를 관리합니다.
반응형 UI 디자인
Java Swing을 사용해 Twitter와 유사한 UI를 제공합니다.
💾 설치 및 실행 방법
1. 필요한 도구 설치
Java JDK 8 이상
Eclipse IDE
MySQL Community Server
2. 프로젝트 클론
bash
코드 복사
git clone https://github.com/eun0211/DB-24-2-TermProject.git
cd DB-24-2-TermProject
3. 데이터베이스 설정
MySQL에서 아래 명령어로 데이터베이스 및 테이블 생성:

sql
코드 복사
CREATE DATABASE twitter_clone;
USE twitter_clone;

CREATE TABLE posts (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(255),
    writter_id VARCHAR(50),
    num_of_likes INT
);

CREATE TABLE comment (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id INT,
    user_id VARCHAR(50),
    text VARCHAR(255),
    FOREIGN KEY (post_id) REFERENCES posts(post_id)
);
초기 데이터를 삽입합니다:

sql
코드 복사
INSERT INTO posts (text, writter_id, num_of_likes) VALUES
('Hello World!', 'user1', 5),
('Java is awesome!', 'user2', 10);

INSERT INTO comment (post_id, user_id, text) VALUES
(1, 'user3', 'Nice post!'),
(1, 'user4', 'I agree!');
4. 프로젝트 실행
Eclipse IDE에서 File → Open Projects from File System을 선택하여 프로젝트를 열어줍니다.
MainApp.java를 실행합니다.
UI에서 트윗을 작성하거나 좋아요, 댓글 기능을 테스트합니다.
⚙️ 기술 스택
프론트엔드: Java Swing
백엔드: MySQL
라이브러리: JDBC (Java Database Connectivity)
통합 개발 환경: Eclipse IDE
📌 구현한 주요 코드
1. PostService.java
데이터베이스와 상호작용하여 게시글 및 댓글 데이터를 가져오고 추가하는 로직이 포함되어 있습니다.
주요 메서드:
getPosts(): 데이터베이스에서 게시글을 가져옵니다.
addComment(post_id, user_id, text): 특정 게시글에 댓글을 추가합니다.
likePost(post_id): 게시글의 좋아요 수를 증가시킵니다.
2. MainApp.java
전체 애플리케이션 UI를 구성하고, 중앙의 타임라인, 왼쪽 메뉴 바, 오른쪽 트렌드 패널을 관리합니다.
중앙 타임라인은 PostService에서 데이터를 가져와 동적으로 UI를 렌더링합니다.
💡 향후 추가할 기능
회원가입 및 로그인 기능
유저 인증을 추가하여 다수의 사용자 관리.
팔로우/언팔로우 기능
다른 사용자를 팔로우하거나 언팔로우.
프로필 페이지
사용자별 프로필 및 작성 글 확인 기능.
실시간 데이터 업데이트
게시글 및 댓글의 실시간 업데이트를 WebSocket을 통해 구현.
📧 문의
궁금한 점이나 개선 사항은 아래로 문의해 주세요:

GitHub Issues: DB-24-2-TermProject
