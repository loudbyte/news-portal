import http from "../http-common";

var token = Buffer.from(`${sessionStorage.getItem('login')}:${sessionStorage.getItem('password')}`, 'utf8').toString('base64')

class NewsDataService {
  getAll() {
    return http.get("/news", {
      headers: {
        'Authorization': `Basic ${token}`  
      }
    });
  }

  getByLang(lang) {
    return http.get(`/news?lang=${lang}`, {
      headers: {
        'Authorization': `Basic ${token}`  
      }
    });
  }

  get(id) {
    return http.get(`/news/${id}`, {
      headers: {
        'Authorization': `Basic ${token}`  
      }
    });
  }

  create(data) {
    return http.post("/news", data, {
      headers: {
        'Authorization': `Basic ${token}`  
      }
    });
  }

  update(id, data) {
    return http.post(`/news`, data, {
      headers: {
        'Authorization': `Basic ${token}`  
      }
    });
  }

  delete(id) {
    return http.delete(`/news/${id}`, {
      headers: {
        'Authorization': `Basic ${token}`  
      }
    });
  }

  deleteList(data) { 
    return http.put(`/news`, data, {
      headers: {
        'Authorization': `Basic ${token}`  
      }
    });
  }

  login() {
    token = Buffer.from(`${sessionStorage.getItem('login')}:${sessionStorage.getItem('password')}`, 'utf8').toString('base64');

    return http.get(`/news`,  {
      headers: {
        'Authorization': `Basic ${token}`  
      }
    })
  }

  logout() {
    sessionStorage.clear();
    window.location.reload();
    window.location.assign("/")

  }
}

export default new NewsDataService();