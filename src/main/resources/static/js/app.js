// app.js (Express 서버)
const express = require('express');
const mariadb = require('mariadb');

const app = express();

// MariaDB 연결 풀 생성
const pool = mariadb.createPool({
    host: 'db.newlecture.com',
    port: 3306,
    user: 'iljo',
    password: '1234',
    database: 'iljodb'
  });

// 회원가입 API 엔드포인트
app.post('/signup', (req, res) => {
  // 사용자 정보 추출
  const { kakaoId, name, email } = req.body;

  // MariaDB에 사용자 정보 저장
  pool.getConnection()
    .then((conn) => {
      conn.query('INSERT INTO users (kakaoId,genderId,username, email) VALUES (?, ?, ?)', [kakaoId,genderId,name, email])
        .then(() => {
          console.log('User saved to MariaDB');
          conn.release();
          res.status(200).send('User registered successfully');
        })
        .catch((error) => {
          console.error('Failed to save user to MariaDB:', error);
          conn.release();
          res.status(500).send('Failed to register user');
        });
    })
    .catch((error) => {
      console.error('Failed to connect to MariaDB:', error);
      res.status(500).send('Failed to connect to the database');
    });
});

app.listen(3000, () => {
  console.log('Server is running on port 3000');
});
