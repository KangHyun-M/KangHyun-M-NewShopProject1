import React, { useState } from "react";
import axios from "axios";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const handleLgoin = async (e) => {
    e.preventDefault();
    const body = {
      username: username,
      password: password,
    };

    axios
      .post("/login", body, { withCredentials: true })
      .then((res) => {
        if (res.status === 200) {
          let accessToken = res.headers["authorization"];
          console.log(accessToken);
        }
      })
      .catch((res) => {
        console.log(res);
      });
  };
  return (
    <div>
      <form onSubmit={handleLgoin}>
        <div>
          <input
            onChange={(e) => setUsername(e.target.value)}
            type="id"
            placeholder="아이디"
          />
        </div>
        <div>
          <input
            onChange={(e) => setPassword(e.target.value)}
            type="password"
            placeholder="비밀번호"
          />
        </div>
        <button onClick={handleLgoin} type="submit">
          login
        </button>
      </form>
    </div>
  );
}
