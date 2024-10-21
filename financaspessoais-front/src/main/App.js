import React from "react";

import 'bootswatch/dist/flatly/bootstrap.css';

class App extends React.Component {
  render() {
    return (
      <div>
        <h1>Finanças Pessoais</h1>
        <button type="button" className="btn btn-primary">Primary</button>
        <button type="button" className="btn btn-secondary">Secondary</button>
        <button type="button" className="btn btn-success">Success</button>
        <button type="button" className="btn btn-info">Info</button>
        <button type="button" className="btn btn-warning">Warning</button>
        <button type="button" className="btn btn-danger">Danger</button>
        <button type="button" className="btn btn-light">Light</button>
        <button type="button" className="btn btn-dark">Dark</button>
        <button type="button" className="btn btn-link">Link</button>
      </div>
    );
  }
}

export default App;