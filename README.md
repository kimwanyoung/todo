## 일정관리 앱 만들기
### 기능 요구사항
#### 1. 일정 생성
- 이름, 이메일, 할일, 비밀번호 필요 
#### 2. 일정 조회
- 전달받은 id로 조회
#### 2-1. 일정 전체 조회
- 수정일 기준 내림차순 정렬
#### 3. 일정 수정
- 생성 시 전달한 비밀번호와 수정 시 전달한 비밀번호 일치하면 삭제
- 할일, 작성자 명만 수정가능 수정 완료되면 updatedAt 완료된 시간으로 업데이트
#### 4. 일정 삭제
- 생성 시 전달한 비밀번호와 삭제 시 전달한 비밀번호 일치하면 삭제
#### 5. 페이지네이션

### API 명세
https://documenter.getpostman.com/view/24686570/2sAXqzXJaM

### ERD
![todo_erd.png](https://befitting-subway-0bf.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2Fec696ef1-489c-4a9e-b954-fe7608e4327d%2F21c6e3bb-314c-42f5-994a-3f509aeac6cb%2Ftodo_erd.png?table=block&id=1111b23a-23bc-80c0-b87a-d38ee29909f0&spaceId=ec696ef1-489c-4a9e-b954-fe7608e4327d&width=1420&userId=&cache=v2)