import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8080/news-portal/api",
  headers: {
    "Content-type": "application/json"
  }
});