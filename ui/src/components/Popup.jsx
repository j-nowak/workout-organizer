import React from 'react';
import "./Popup.css";

class Popup extends React.Component {
  render() {
    if (!this.props.show) {
      return null;
    }

    const style = { display: 'flex', justifyContent: 'center', alignItems: 'center'}

    return (
      <div className="popup-backdrop" style={style} onClick={this.props.onClose}>
        <div className="popup" onClick={(ev) => ev.stopPropagation() }>
          <button className="popup-close" onClick={this.props.onClose}>✖</button>
          {this.props.children}
        </div>
      </div>
    );
  }
}

export default Popup;