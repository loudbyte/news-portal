import http from "../http-common";

class NewsDataService {
  getAll() {
    return http.get("/news");
  }

  get(id) {
    return http.get(`/news/${id}`);
  }

  create(data) {
    return http.post("/news", data);
  }

  update(id, data) {
    return http.post(`/news`, data);
  }

  delete(id) {
    return http.delete(`/news/${id}`);
  }

  deleteList(data) {
    return http.delete(`/news`, ["1", "2", "3"]);
  }

  login(data) {
    return http.get(`/login`, data);
  }

  // deleteAll() {
  //   return http.delete(`/news`);
  // }

  // findByTitle(title) {
  //   return http.get(`/news?title=${title}`);
  // }
}

export default new NewsDataService();