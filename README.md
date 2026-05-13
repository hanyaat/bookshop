# 書籍購入API

Spring Boot + MySQL + Docker で構築した書籍購入APIです。   
DBカラム設計から考えてみたいと思い作成しました。

## 技術スタック

| 技術 | バージョン |
|---|---|
| Java | 21 |
| Spring Boot | 3.5.13 |
| MySQL | 8.0 |
| Docker | - |

## 機能一覧

- ユーザー登録・取得・更新・削除
- 書籍登録・一覧・検索・更新・削除
- カート追加・取得・削除
- 注文（カートの中身をもとに注文作成・カート自動削除）
- 注文履歴取得

## ER図

```mermaid
erDiagram
  USERS ||--o{ ORDERS : "注文する"
  USERS ||--o{ CARTS : "カートを持つ"
  BOOKS ||--o{ ORDER_ITEMS : "注文明細に含まれる"
  BOOKS ||--o{ CARTS : "カートに追加される"
  ORDERS ||--o{ ORDER_ITEMS : "明細を持つ"

  USERS {
    bigint id PK
    string name
    string email
    string password
    string address
    string gender
    int age
    string payment_method
  }
  BOOKS {
    bigint id PK
    string title
    string author
    int published_year
    string description
    int page_count
    int price
    int stock
  }
  ORDERS {
    bigint id PK
    bigint user_id FK
    string order_number
    datetime ordered_at
    int total_price
  }
  ORDER_ITEMS {
    bigint id PK
    bigint order_id FK
    bigint book_id FK
    int quantity
    int unit_price
  }
  CARTS {
    bigint id PK
    bigint user_id FK
    bigint book_id FK
    int quantity
  }
```

## API一覧

| メソッド | URL | 内容 |
|---|---|---|
| POST | `/api/users` | ユーザー登録 |
| GET | `/api/users/{id}` | ユーザー取得 |
| PUT | `/api/users/{id}` | ユーザー更新 |
| DELETE | `/api/users/{id}` | ユーザー削除 |
| GET | `/api/books` | 書籍一覧 |
| GET | `/api/books/{id}` | 書籍1件取得 |
| GET | `/api/books/search?title=xxx` | タイトル検索 |
| GET | `/api/books/price?max=xxx` | 価格以下検索 |
| POST | `/api/books` | 書籍登録 |
| PUT | `/api/books/{id}` | 書籍更新 |
| DELETE | `/api/books/{id}` | 書籍削除 |
| GET | `/api/cart/{userId}` | カート取得 |
| POST | `/api/cart` | カートに追加 |
| DELETE | `/api/cart/{userId}/{bookId}` | カートから削除 |
| POST | `/api/orders` | 注文 |
| GET | `/api/orders/{userId}` | 注文履歴取得 |
| GET | `/api/orders/detail/{id}` | 注文詳細取得 |

## DB設計

### usersテーブル
| カラム名 | 型 | 説明 |
|---|---|---|
| id | bigint | 主キー |
| name | varchar | 氏名 |
| email | varchar | メールアドレス |
| password | varchar | パスワード |
| address | varchar | 住所 |
| gender | varchar | 性別 |
| age | int | 年齢 |
| payment_method | varchar | 支払い方法 |

### booksテーブル
| カラム名 | 型 | 説明 |
|---|---|---|
| id | bigint | 主キー |
| title | varchar | タイトル |
| author | varchar | 著者 |
| published_year | int | 出版年 |
| description | varchar | 概要 |
| page_count | int | ページ数 |
| price | int | 価格 |
| stock | int | 在庫数 |

### ordersテーブル
| カラム名 | 型 | 説明 |
|---|---|---|
| id | bigint | 主キー |
| user_id | bigint | 外部キー（users） |
| order_number | varchar | 注文番号 |
| ordered_at | datetime | 注文日時 |
| total_price | int | 合計金額 |

### order_itemsテーブル
| カラム名 | 型 | 説明 |
|---|---|---|
| id | bigint | 主キー |
| order_id | bigint | 外部キー（orders） |
| book_id | bigint | 外部キー（books） |
| quantity | int | 数量 |
| unit_price | int | 単価 |

### cartsテーブル
| カラム名 | 型 | 説明 |
|---|---|---|
| id | bigint | 主キー |
| user_id | bigint | 外部キー（users） |
| book_id | bigint | 外部キー（books） |
| quantity | int | 数量 |

## 起動方法

```bash
docker compose up --build
```

<img width="1099" height="285" alt="スクリーンショット 2026-05-11 141126" src="https://github.com/user-attachments/assets/20c63380-e024-42f9-bf57-25461a960317" />


## 動作確認

### ユーザー登録
<img width="1426" height="775" alt="スクリーンショット 2026-05-11 150729" src="https://github.com/user-attachments/assets/ec648223-e5f4-4cac-b993-86fe2d696428" />


### 書籍登録
<img width="1413" height="674" alt="スクリーンショット 2026-05-13 161446" src="https://github.com/user-attachments/assets/d7ae0911-3c1f-44e7-928c-e438e870b525" />


### カートに追加
<img width="1424" height="900" alt="スクリーンショット 2026-05-13 161520" src="https://github.com/user-attachments/assets/12187898-b6a6-45b5-a2da-4b369fffa9ed" />



### 注文
<img width="1413" height="945" alt="スクリーンショット 2026-05-13 161551" src="https://github.com/user-attachments/assets/9baa959e-eb27-42eb-a855-8462cc802ac0" />


### 備忘録
postmanで動作確認をした際、orderとorderitemがループしてしまったため@JsonManagedReference と @JsonBackReferenceを追加

### 今後
フロントも作成予定
