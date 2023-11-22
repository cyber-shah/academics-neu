/* global use, db */
// MongoDB Playground
// To disable this template go to Settings | MongoDB | Use Default Template For Playground.
// Make sure you are connected to enable completions and to be able to run a playground.
// Use Ctrl+Space inside a snippet or a string literal to trigger completions.
// The result of the last command run in a playground is shown on the results panel.
// By default the first 20 documents will be returned with a cursor.
// Use 'console.log()' to print to the debug output.
// For more documentation on playgrounds please refer to
// https://www.mongodb.com/docs/mongodb-vscode/playgrounds/

// Select the database to use.
use('sample_airbnb');



// Write a filter to return all documents where the listed property type is Chalet 
// and the chalet has at least 1 bedroom. 
db.listingsAndReviews.find({
    property_type: 'Chalet',
    bedrooms: { $gte: 1 }
});

// How many records are returned for question 1?
db.listingsAndReviews.countDocuments({
    property_type: 'Chalet',
    bedrooms: { $gte: 1 }
});







// Write a filter that returns all documents that are located in Turkey 
// and where the host is classified as a superhost. 
db.listingsAndReviews.find(
    { 'address.country': 'Turkey', 'host.host_is_superhost': true });
    
// How many records are returned for question 3?
db.listingsAndReviews.countDocuments({
    'address.country': 'Turkey',
    'host.host_is_superhost': true
});






// Write a filter to return all documents that can accommodate exactly 2 people. 
db.listingsAndReviews.find(
    { accommodates: { $eq: 2 }, extra_people : {$eq : 0.00} },
);

// How many records are returned for question 7?
db.listingsAndReviews.countDocuments(
    { accommodates: { $eq: 2 }, extra_people: { $eq: 0.00 } }
);






// Write a filter to return all documents where the 
// cancellation policy is "moderate" and the price is less than $50.
db.listingsAndReviews.find(
    {cancellation_policy: 'moderate', price: {$lte : 50}}
);

// How many records are returned for question 9?
db.listingsAndReviews.countDocuments(
    {cancellation_policy: 'moderate', price: {$lte : 50}}
);






// Write a filter to return all documents where the amenities 
// contain "TV" and "Wifi".
db.listingsAndReviews.find(
    { amenities: { $all: ["TV", "Wifi"] } }
);

// How many records are returned for question 11?
db.listingsAndReviews.countDocuments(
    { amenities: { $all: ["TV", "Wifi"] } }
);







// Write a filter to return all documents where the number of bedrooms is 
// not provided(null or undefined).
const thirteen = 
{
    $or: [
        { bedrooms: null },
        { bedrooms: undefined }
    ]
};
db.listingsAndReviews.find(thirteen);

// How many records are returned for question 13?
db.listingsAndReviews.find(thirteen).count();





// Write a filter to return all documents where the host location is 
// "Montreal, Quebec, Canada" and the host has been verified via a  
// “government_id” and a “phone”. 
const fifteen = 
    {
        'address.street': 'Montréal, Québec, Canada',
        'host.host_verifications': {
            $all: [
                "government_id",
                "phone"
            ]
        }
    };
db.listingsAndReviews.find(fifteen);

// How many records are returned for question 15?
db.listingsAndReviews.find(fifteen).count();






// Write a filter to return all documents where the property type is  
// Serviced apartment and have a cleanliness review score > 9. 
const seventeen =
    {
        property_type: "Serviced apartment",
        'review_scores.review_scores_cleanliness': { $gt: 9 }
    };
db.listingsAndReviews.find(seventeen);

// How many records are returned for question 17?
db.listingsAndReviews.find(seventeen).count();







// Write a filter to return all documents where cancellation policy is 
// “flexible” or “super_strict_60” and  picture urls for images are provided.
const nineteen = 
{
    $or : [
        {cancellation_policy: 'flexible'},
        {cancellation_policy : 'super_strict_60'}
    ],
    'images.picture_url' : {$exists : true}
}
db.listingsAndReviews.find(nineteen);

// How many records are returned for question 19?
db.listingsAndReviews.find(nineteen).count();







// Write a filter to return all documents mentioning the word  “clean” in the reviews and 
// having a review score cleanliness greater than 9. 
const twentyOne =
{
    'review_scores.review_scores_cleanliness': { $gt: 9 },
    'reviews.comments': {
        $regex: /clean/
    }
};
db.listingsAndReviews.find(twentyOne);

// How many records are returned for question 21? 
db.listingsAndReviews.find(twentyOne).count();




// Write a filter to return all documents with property type “Resort” 
// that have all of  the following amenities: “Wifi”, “Hot tub”, “Wheelchair accessible”. 
const twentyThree = 
{
    property_type: "Resort",
    amenities: {
        $all : [
            "Wifi",
            "Hot tub",
            "Wheelchair accessible"
        ]
    }
}
db.listingsAndReviews.find(twentyThree);


// How many records are returned for question 23? 
db.listingsAndReviews.find(twentyThree).count();




// Write a filter to return all documents where the host name is  “Ali”, 
// and the identity is verified.(host_identity_verified) 
const twentyFive = 
{
    'host.host_name': { $eq: "Ali" },
    "host.host_identity_verified" : true
}
db.listingsAndReviews.find(twentyFive);

// How many records are returned for question 25? 
db.listingsAndReviews.find(twentyFive).count();







// Write a filter to return all documents where the first amenity listed is “Wifi”. 
const twentySeven = {
    "amenities.0": "Wifi"
}
db.listingsAndReviews.find(twentySeven);
// How many records are returned for question 27? 
db.listingsAndReviews.find(twentySeven).count();






// Write  a filter to return all documents where one of the amenities begins with the letter ‘H”. 
const twentyNine = {
    "amenities": {
        $regex: /^H/
    }
}
db.listingsAndReviews.find(twentyNine);
// How many records are returned for question 29? 
db.listingsAndReviews.find(twentyNine).count();






// Write a filter to return all documents.
const thirtyOne =
{

}
db.listingsAndReviews.find(thirtyOne);

// // How many records are returned for question 31?
db.listingsAndReviews.find(thirtyOne).count();







// Write a filter that returns all documents that have a market equal to: 
// “Hong Kong”, “New York”, “Porto”,  “Sydney” or “Istanbul” in the address field.
const thirtyTwo = {
  $or: [
    { "address.market": "Hong Kong" },
    { "address.market": "New York" },
    { "address.market": "Porto" },
    { "address.market": "Sydney" },
    { "address.market": "Istanbul" }
  ]
};
db.listingsAndReviews.find(thirtyTwo);

// How many records are returned for question 33? 
db.listingsAndReviews.find(thirtyTwo).count();