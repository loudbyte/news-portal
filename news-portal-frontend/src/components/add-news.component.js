import React, { Component } from "react";
import NewsDataService from "../services/news.service";

export default class AddNews extends Component {
  constructor(props) {
    super(props);
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.onChangeBrief = this.onChangeBrief.bind(this);
    this.onChangeContent = this.onChangeContent.bind(this);
    this.onChangeLanguage = this.onChangeLanguage.bind(this);
    this.onChangeDate = this.onChangeDate.bind(this);
    this.saveNews = this.saveNews.bind(this);

    this.state = {
      id: null,
      title: "",
      brief: "", 
      content: "",
      language: "",
      newsDate: "",
      language: "",

      submitted: false
    };
  }

  onChangeTitle(e) {
    this.setState({
      title: e.target.value
    });
  }

  onChangeBrief(e) {
    this.setState({
      brief: e.target.value
    });
  }

  onChangeContent(e) {
    this.setState({
      content: e.target.value
    });
  }
  
  onChangeLanguage(e) {
    this.setState({
      language: e.target.value
    });
  }

  onChangeDate(e) {
    this.setState({
      newsDate: e.target.value
    });
  }

  saveNews() {
    var data = {
      title: this.state.title,
      brief: this.state.brief,
      content: this.state.content,
      newsDate: this.state.newsDate,
      language: this.state.language
    };

    NewsDataService.create(data)
      .then(response => {
        this.setState({
          id: response.data.id,
          title: response.data.title,
          brief: response.data.brief,
          content: response.data.content,
          newsDate: response.data.newsDate,
          language: response.data.language,

          submitted: true
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() {
    return (
      <div>
        {this.state.submitted ? (
          <div>
            <h4>You submitted successfully!</h4>
          </div>
        ) : (
          <div>
            <div>
              <label htmlFor="title">Title</label>
              <input
                type="text"
                className="form-control"
                id="title"
                required
                value={this.state.title}
                onChange={this.onChangeTitle}
                name="title"
              />
            </div>

            <div>
              <label htmlFor="brief">Brief</label>
              <textarea rows="3"
                type="text"
                className="form-control"
                id="brief"
                required
                value={this.state.brief}
                onChange={this.onChangeBrief}
                name="brief"
              />
            </div>

            <div>
              <label htmlFor="content">Content</label>
              <textarea rows="6"
                type="text"
                className="form-control"
                id="content"
                required
                value={this.state.content}
                onChange={this.onChangeContent}
                name="content"
              />
            </div>

            <div>
              <label htmlFor="language">Language</label>
              <textarea rows="1"
                type="text"
                className="form-control"
                id="language"
                required
                value={this.state.language}
                onChange={this.onChangeLanguage}
                name="language"
              />
            </div>

            <div>
              <label htmlFor="newsDate">Date</label>
              <input
                type="date"
                className="form-control"
                id="newsDate"
                required
                value={this.state.newsDate}
                onChange={this.onChangeDate}
                name="newsDate"
              />
            </div>

            <button onClick={this.saveNews} className="btn my-button btn-success">
              Save
            </button>
            <a href="/news" className="btn my-button btn-primary" >
              Cancel
            </a>
          </div>
        )}
      </div>
    );
  }
}