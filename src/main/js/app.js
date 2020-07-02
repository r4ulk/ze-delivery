'use strict';

const React = require('react');
const ReactDOM = require('react-dom');

import {
  withGoogleMap,
  GoogleMap,
  InfoWindow,
  Marker,
} from "react-google-maps";

import PlacesAutocomplete, { geocodeByAddress, getLatLng } from 'react-places-autocomplete';

const MapWithAMarker = withGoogleMap(props => {
    return (
      <GoogleMap
        defaultZoom={12}
        defaultCenter={{ lat: props.latitude, lng: props.longitude }}
        center={{ lat: props.latitude, lng: props.longitude }}
        onClick={props.onMapClick}>
        <StoresLayer
            stores={props.stores}
            handleInfoClick={props.onMarkerClick} />
        {props.stores.map((store, index) => (
            <Marker
                key={index}
                position={{ lat: store.address.coordinates[0][0], lng: store.address.coordinates[0][1] }}
                onClick={() => props.onMarkerClick(store)}
                label={(index+1).toString()}>
                {store.showInfo && (
                    <InfoWindow onCloseClick={() => props.onMarkerClose(store)}>
                        <div id="infoWindow">
                            <b>{store.tradingName}</b> ({store.document})<br />
                            {store.ownerName}
                        </div>
                    </InfoWindow>
                )}
            </Marker>
      ))}
      </GoogleMap>
    )
});

class StoresLayer extends React.Component {

    render() {
		return (
			<div className="storesLayer">
			    {this.props.stores.map((store, index) => (
                    <StoreLayer
                        key={index}
                        store={store}
                        index={index}
                        handleInfoClick={this.props.handleInfoClick}/>
                ))}
            </div>
		)
	}

}

class StoreLayer extends React.Component {

    render() {
		return (
            <div className="strLayer">
                <a onClick={() => this.props.handleInfoClick(this.props.store)}><b>{this.props.index+1}. {this.props.store.tradingName}</b></a>
                <p>
                    {this.props.store.document} - {this.props.store.ownerName}
                </p>
            </div>
		)
	}

}

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
		    stores: [],
		    address: 'Barra da Tijuca, Rio de Janeiro - RJ, 22640-100',
		    longitude: -43.36556,
            latitude: -22.99669
		};
		this.handleMapClick = this.handleMapClick.bind(this);
		this.handleMarkerClick = this.handleMarkerClick.bind(this);
		this.handleMarkerClose = this.handleMarkerClose.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSelect = this.handleSelect.bind(this);
	}

	componentDidMount() {
        fetch('/stores?lng='+this.state.longitude+'&lat='+this.state.latitude , { method: 'GET' })
            .then(response => response.json())
            .then(function(response) {
            this.setState({stores: response});
        }.bind(this));
	}

    handleMapClick(event) {
        this.setState({
            latitude: event.latLng.lat(),
            longitude: event.latLng.lng(),
        })
        fetch('/stores?lng='+this.state.longitude+'&lat='+this.state.latitude , { method: 'GET' })
            .then(response => response.json())
            .then(function(response) {
            this.setState({stores: response});
        }.bind(this));
    }

    handleMarkerClick(targetStore) {
        this.setState({
            stores: this.state.stores.map(store => {
                store.showInfo = false
                if(store == targetStore) store.showInfo = true
                return store;
            })
        })
    }

    handleMarkerClose(targetStore) {
        this.setState({
            stores: this.state.stores.map(store => {
                if(store == targetStore) store.showInfo = false
                return store;
            }),
        })
    }

    handleSelect(addressName) {
        this.setState({
          address: addressName,
        });

        geocodeByAddress(addressName)
            .then(results => getLatLng(results[0]))
            .then(({ lat, lng }) => {
                console.log('Geocode Success', { lat, lng }); // eslint-disable-line no-console
                this.setState({
                    latitude: lat,
                    longitude: lng,
                });
                fetch('/stores?lng='+this.state.longitude+'&lat='+this.state.latitude , { method: 'GET' })
                    .then(response => response.json())
                    .then(function(response) {
                        this.setState({stores: response});
                    }.bind(this))
                })
            .catch(error => {
                console.log('Geocode Error', error); // eslint-disable-line no-console
            });
    }

    handleChange(addressName) {
        this.setState({ address: addressName });
    }

  	render() {

  	    const inputSearchProps = {
          value: this.state.address,
          onChange: this.handleChange,
        }

        const cssClasses = {
            root: 'rootAutocomplete',
            input: 'searchInputByAddress',
            autocompleteContainer: 'autocompleteContainer'
        }

        const renderSuggestion = ({ formattedSuggestion }) => (
          <div className="suggestionItem">
            <i className="fa fa-map-marker suggestionIcon" />
            <strong>{formattedSuggestion.mainText}</strong>{' '}
            <small className="textMuted">{formattedSuggestion.secondaryText}</small>
          </div>
        );

		return (
		    <div>
                <div className="center">
                    <div className="zeDeliveryStore">
                        <img src="logo-white-text.png" width="150px" />
                    </div>
                    <div id="inputSearch" className="inputSearch">
                        <PlacesAutocomplete
                            onSelect={this.handleSelect}
                            inputProps={inputSearchProps}
                            classNames={cssClasses}
                            renderSuggestion={renderSuggestion}  />
                    </div>
                    <div className="author">by Klumpp, Raul - raulklumpp@gmail.com</div>
                </div>
                <div id="containerMap">
                    <MapWithAMarker
                        latitude={this.state.latitude}
                        longitude={this.state.longitude}
                        stores={this.state.stores}
                        onMapClick={this.handleMapClick}
                        onMarkerClick={this.handleMarkerClick}
                        onMarkerClose={this.handleMarkerClose}
                        containerElement={<div style={{ height: "600px" }} />}
                        mapElement={<div style={{ height: "100%" }} />}
                    />
                </div>
            </div>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)
