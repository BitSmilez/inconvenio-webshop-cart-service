# inconvenio-webshop-cart-service

This is the Cart Microservice, responsible for managing user's shopping carts and processing checkout requests. It provides a RESTful API to handle cart management operations such as adding, updating, and removing products, as well as processing checkout requests through a message queue.
Features

    Get cart products
    Process checkout requests
    Message queue integration

Requirements

    Java 11+
    Spring Boot
    Docker

Installation

Clone the repository:


    git clone https://github.com/BitSmilez/inconvenio-webshop-cart-service.git




Usage

Navigate to the project directory:

To start the Cart Microservice using Docker, run the following command:

bash

docker-compose up

This will start the microservice and any required infrastructure, such as a message queue, in separate containers. The API will be accessible on http://localhost:8081.
API Endpoints

    GET /cart/{cartID}: Get products in the cart
    POST /checkout: Create a checkout request and send it to the message queue
