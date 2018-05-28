

import React, { Component } from "react";
import ReactDOM from 'react-dom';
import './index.css';
import axios from "axios";

// ========================================

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {country: '', temperature:0, wind:0, humidity:0};
    }

    weatherCallback = (weatherData) => {
        console.log(weatherData);
        this.setState({country: weatherData.country,
            temperature:weatherData.temperature,
            wind:weatherData.windspeed,
            humidity:weatherData.humidity});
    }

    render() {
        return (
            <div className="App">
                <div><WeatherForm weatherFormCallback={this.weatherCallback}></WeatherForm></div>
                <div><Result country={this.state.country} temperature={this.state.temperature} wind={this.state.wind} humidity={this.state.humidity}></Result></div>
            </div>

        );
    }
}

export default App;


class WeatherForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {city: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({city: event.target.value});
    }

    handleSubmit(event) {
        axios
            .get("http://localhost:8080/weather/"+this.state.city, {
                method: 'GET',
                headers: {
                    'Access-Control-Allow-Origin': '*',
                    'Content-Type': 'application/json',
                }
            })
            .then(response => {
                this.props.weatherFormCallback(response.data);
            })
            .catch(error => console.log(error));
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    City:
                    <input type="text" value={this.state.city} onChange={this.handleChange} />
                </label>
                <input type="submit" value="Fetch" />
            </form>
        );
    }
}
class Result extends React.Component {
    constructor(props) {
        super(props);
    }

    render(){
        return(
            <div id="results">
                <div><span>Country: </span><span id="resultCountry">{this.props.country}</span></div>
                <div><span>Temperature: </span><span id="resultTemp">{this.props.temperature}</span><span> `C</span></div>
                <div><span>Wind: </span><span id="resultWind">{this.props.wind}</span><span> m/s</span></div>
                <div><span>Humidity: </span><span id="resultHumidity">{this.props.humidity}</span><span> `%</span></div>
            </div>
        )
    }
}




ReactDOM.render(
    <App />,
    document.getElementById('root')
);