class BaseError extends Error {
  constructor(err) {
    super(arguments);

    let message;
    if (err.error) {
      message = err.error.message;
    } else if (err.data && err.data.error) {
      message = err.data.error.message;
    } else if (err.data && err.data.reasons) {
      message = err.data.reasons[0].message
    } else {
      message = err;
    }

    this.message = message;
    this.name = 'BaseError';
  }
}

class HTTPError extends BaseError {
  constructor(err) {
    super(err);
    this.status = status;
    this.name = 'HTTPError';
  }
}

class ServiceError extends BaseError {
  constructor(err) {
    super(err);
    if (err.code) { this.code = err.code }
    if (err.config) { this.config = err.config }
    this.name = 'ServiceError';
  }
}

