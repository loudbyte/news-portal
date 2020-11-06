import React, { Component } from "react";
import NewsDataService from "../services/news.service";

export default class EditNews extends Component {
  constructor(props) {
    super(props);
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.onChangeBrief = this.onChangeBrief.bind(this);
    this.onChangeContent = this.onChangeContent.bind(this);
    this.onChangeLanguage = this.onChangeLanguage.bind(this);
    this.onChangeDate = this.onChangeDate.bind(this);
    this.getNews = this.getNews.bind(this);
    this.updateNews = this.updateNews.bind(this);
    this.deleteNews = this.deleteNews.bind(this);

    this.state = {
      currentNews: {
        id: null,
        title: "",
        brief: "",
        content: "",
        language: "",
        newsDate: ""
      },
      message: ""
    };
  }

  componentDidMount() {
    this.getNews(this.props.match.params.id);
  }

  
  setActiveNews(news, index) {
    this.setState({
      currentNews: news,
      currentIndex: index
    });
  }

  onChangeTitle(e) {
    const title = e.target.value;

    this.setState(function(prevState) {
      return {
        currentNews: {
          ...prevState.currentNews,
          title: title
        }
      };
    });
  }

  onChangeBrief(e) {
    const brief = e.target.value;
    
    this.setState(prevState => ({
      currentNews: {
        ...prevState.currentNews,
        brief: brief
      }
    }));
  }

  onChangeContent(e) {
    const content = e.target.value;
    
    this.setState(prevState => ({
      currentNews: {
        ...prevState.currentNews,
        content: content
      }
    }));
  }

  onChangeLanguage(e) {
    const language = e.target.value;
    
    this.setState(prevState => ({
      currentNews: {
        ...prevState.currentNews,
        language: language
      }
    }));
  }

  onChangeDate(e) {
    const newsDate = e.target.value;
    
    this.setState(prevState => ({
      currentNews: {
        ...prevState.currentNews,
        newsDate: newsDate
      }
    }));
  }

  getNews(id) {
    NewsDataService.get(id)
      .then(response => {
        this.setState({
          currentNews: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  updateNews() {
    NewsDataService.update(
      this.state.currentNews.id,
      this.state.currentNews
    )
      .then(response => {
        console.log(response.data);
        this.setState({
          message: "The news was updated successfully!"
        });
      })
      .catch(e => {
        console.log(e);
      });
  }

  deleteNews() {    
    NewsDataService.delete(this.state.currentNews.id)
      .then(response => {
        console.log(response.data);
        this.props.history.push('/news')
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() {
    const { currentNews } = this.state;

    return (
      <div>
        {currentNews ? (
          <div>
            <form>
              <div className="form-group">
                <label htmlFor="title">Title</label>
                <input
                  type="text"
                  className="form-control"
                  id="title"
                  value={currentNews.title}
                  onChange={this.onChangeTitle}
                />
              </div>
              <div className="form-group">
                <label htmlFor="brief">Brief</label>
                <textarea rows="3"
                  type="text"
                  className="form-control"
                  id="brief"
                  value={currentNews.brief}
                  onChange={this.onChangeBrief}
                />
              </div>

              <div className="form-group">
                <label htmlFor="content">Content</label>
                <textarea rows="6"
                  type="text"
                  className="form-control"
                  id="content"
                  value={currentNews.content}
                  onChange={this.onChangeContent}
                />
              </div>

              <div className="form-group">
                <label htmlFor="language">Language</label>
                <textarea rows="1"
                  type="text"
                  className="form-control"
                  id="language"
                  value={currentNews.language}
                  onChange={this.onChangeLanguage}
                />
              </div>

              <div className="form-group">
                <label htmlFor="newsDate">Date</label>
                <input
                type="text"
                  className="form-control"
                  id="newsDate"
                  value={currentNews.newsDate}
                  onChange={this.onChangeDate}
                />
              </div>
            </form>

            <button
              className="btn my-button btn-danger"
              onClick={this.deleteNews}
            >
              Delete
            </button>

            <button
              type="submit"
              className="btn my-button btn-warning"
              onClick={this.updateNews}
            >
              Update
            </button>
            <p>{this.state.message}</p>
          </div>
        ) : (
          <div>
            <br />
            <p>Please click on a News...</p>
          </div>
        )}
      </div>
    );
  }
}