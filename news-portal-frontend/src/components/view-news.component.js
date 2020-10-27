import React, { Component } from "react";
import {Card, Col, Row, ListGroup, ButtonGroup} from 'react-bootstrap';
import NewsDataService from "../services/news.service";
import { Link } from "react-router-dom";

export default class ViewNews extends Component {
  constructor(props) {
    super(props);
    this.getNews = this.getNews.bind(this);
    this.updateNews = this.updateNews.bind(this);
    this.deleteNews = this.deleteNews.bind(this);

    this.state = {
      currentNews: {
        id: null,
        title: "",
        brief: "",
        content: "",
        newsDate: ""
      },
      message: ""
    };
  }

  componentDidMount() {
    this.getNews(this.props.match.params.id);
  }

  retrieveNewsList() {
    NewsDataService.getAll()
      .then(response => {
        this.setState({
          news: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
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

  getParsedDate(strDate){
    var strSplitDate = String(strDate).split(',');
    var date = new Date(strSplitDate[0]);
    var dd = date.getDate();
    var mm = date.getMonth() + 1; //January is 0!

    var yyyy = date.getFullYear();
    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }
    date =  dd + "-" + mm + "-" + yyyy;
    return date.toString();
}

  render() {
    const { currentNews } = this.state;

    return (
        <div>
          
        {currentNews ? (
              <ListGroup.Item>
                <Card  align="left">
                  <Card.Header>
                    <Row>
                      <Col>
                        {currentNews.title}
                      </Col>
                      <Col xs={4}>
                        {this.getParsedDate(currentNews.newsDate)}
                      </Col>
                    </Row>
                  </Card.Header>    
                  <Card.Body>
                  <Card.Text>
                    {currentNews.brief}
                    </Card.Text>
                    <Card.Text>
                    {currentNews.content}
                    </Card.Text>
                      <ButtonGroup aria-label="Basic example">
                          <Link
                            to={"/edit/" + currentNews.id}
                            className="link"
                          >
                            Edit
                          </Link>
                          <Link
                            onClick={this.deleteNews}
                            className="link"
                          >
                            Delete
                          </Link>
                      </ButtonGroup>  
                  </Card.Body>
                </Card>
              </ListGroup.Item>
        ) : (
          <div>
            <br />
            <p>Please click on View in the NewsList...</p>
          </div>
        )}
      </div>
    );
  }
}