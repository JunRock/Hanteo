# Question 1
## 기능정리
1. 카테고리ID(식별자)로 조회
2. 카테고리 이름으로 조회
3. 카테고리 생성
4. 게시판 생성
5. 종료
<img width="380" alt="image" src="https://github.com/user-attachments/assets/9bfbea93-aa5f-4cf6-a068-8929271d0735" />

## 📂 카테고리 트리 구조 예시(초기 세팅 환경)

```plaintext
[전체] (categoryId: 1)
├── [공지1] (categoryId: 2)
│   ├── 게시판: 공지사항 (boardId: 1)
│   ├── 게시판: 익명 게시판 (boardId: 3)
│   └── [공지3] (categoryId: 4)
│       └── 게시판: 익명 게시판 (boardId: 3)
└── [공지2] (categoryId: 3)
    ├── 게시판: 공지사항 (boardId: 2)
    └── 게시판: 익명 게시판 (boardId: 3)
```

## 출력 예시
```plaintext
▶ 서브트리 JSON:
{
    "categoryId" : 1,
    "name" : "전체",
    "children" : [
        {
            "categoryId" : 2,
            "name" : "공지1",
            "children" : [
                {
                    "categoryId" : 4,
                    "name" : "공지3",
                    "children" : [ ],
                    "boards" : [
                        {
                            "boardId" : 3,
                            "name" : "익명 게시판"
                        }
                    ]
                }
            ],
            "boards" : [
                {
                    "boardId" : 1,
                    "name" : "공지사항"
                },
                {
                    "boardId" : 3,
                    "name" : "익명 게시판"
                }
            ]
        },
        {
            "categoryId" : 3,
            "name" : "공지2",
            "children" : [ ],
            "boards" : [
                {
                    "boardId" : 2,
                    "name" : "공지사항"
                },
                {
                    "boardId" : 3,
                    "name" : "익명 게시판"
                }
            ]
        }
    ],
    "boards" : [ ]
}
```
