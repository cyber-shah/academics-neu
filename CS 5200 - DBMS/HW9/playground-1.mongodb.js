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
print(db.listingsAndReviews.countDocuments({
    property_type: 'Chalet',
    bedrooms: { $gte: 1 }
}));







// Write a filter that returns all documents that are located in Turkey 
// and where the host is classified as a superhost. 
db.listingsAndReviews.find(
    { 'address.country': 'Turkey', 'host.host_is_superhost': true });
    
// How many records are returned for question 3?
print(db.listingsAndReviews.countDocuments({
    'address.country': 'Turkey',
    'host.host_is_superhost': true
}));






// Write a filter to return all documents that can accommodate exactly 2 people. 
db.listingsAndReviews.find(
    { accommodates: { $eq: 2 }, extra_people : {$eq : 0.00} },
    { _id: 1, accommodates: 1, extra_people: 1 }
);

// How many records are returned for question 7?
print(db.listingsAndReviews.countDocuments(
    { accommodates: { $eq: 2 }, extra_people: { $eq: 0.00 } }
));






// Write a filter to return all documents where the 
// cancellation policy is "moderate" and the price is less than $50.
db.listingsAndReviews.find(
    {cancellation_policy: 'moderate', price: {$lte : 50}}
);

// How many records are returned for question 9?
print(db.listingsAndReviews.countDocuments(
    {cancellation_policy: 'moderate', price: {$lte : 50}}
));






// Write a filter to return all documents where the amenities 
// contain "TV" and "Wifi".
db.listingsAndReviews.find(
    { amenities: { $all: ["TV", "Wifi"] } }
);

// How many records are returned for question 11?
print(db.listingsAndReviews.countDocuments(
    { amenities: { $all: ["TV", "Wifi"] } }
));







// Write a filter to return all documents where the number of bedrooms is 
// not provided(null or undefined).
db.listingsAndReviews.find({
    $or: [
        { bedrooms: null },
        { bedrooms: undefined }
    ]
});

// How many records are returned for question 13?
print(db.listingsAndReviews.countDocuments(
    {$or: [
        { bedrooms: null },
        { bedrooms: undefined }
    ]}
));





// Write a filter to return all documents where the host location is 
// "Montreal, Quebec, Canada" and the host has been verified via a  
// “government_id” and a “phone”. 
const fourteen = 
    {
        'address.street': 'Montréal, Québec, Canada',
        'host.host_verifications': {
            $all: [
                "government_id",
                "phone"
            ]
        }
    };
db.listingsAndReviews.find(fourteen);

// How many records are returned for question 15?
print(db.listingsAndReviews.find(fourteen).count());






// Write a filter to return all documents where the property type is  
// Serviced apartment and have a cleanliness review score > 9. 
const sixteen =
    {
        property_type: "Serviced apartment",
        'review_scores.review_scores_cleanliness': { $gt: 9 }
    };
db.listingsAndReviews.find(sixteen);

// How many records are returned for question 17?
db.listingsAndReviews.find(sixteen).count();







// Write a filter to return all documents where cancellation policy is 
// “flexible” or “super_strict_60” and  picture urls for images are provided.
const eighteen = 
{
    $or : [
        {cancellation_policy: 'flexible'},
        {cancellation_policy : 'super_strict_60'}
    ],
    'images.picture_url' : {$exists : true}
}
db.listingsAndReviews.find(eighteen);

// How many records are returned for question 19?
db.listingsAndReviews.find(eighteen).count();







// Write a filter to return all documents mentioning the word  “clean” in the reviews and 
// having a review score cleanliness greater than 9. 
const twenty =
{
    'review_scores.review_scores_cleanliness': { $gt: 9 },
    'reviews.comments': {
        $regex: /clean/
    }
};
db.listingsAndReviews.find(twenty);

// How many records are returned for question 21? 
db.listingsAndReviews.find(twenty).count();




// Write a filter to return all documents with property type “Resort” 
// that have all of  the following amenities: “Wifi”, “Hot tub”, “Wheelchair accessible”. 
const twentyTwo = 
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
db.listingsAndReviews.find(twentyTwo);


// How many records are returned for question 23? 
db.listingsAndReviews.find(twentyTwo).count();




// Write a filter to return all documents where the host name is  “Ali”, 
// and the identity is verified.(host_identity_verified) 
const twentyFour = 
{
    'host.host_name': { $eq: "Ali" },
    "host.host_identity_verified" : true
}
db.listingsAndReviews.find(twentyFour);

// How many records are returned for question 25? 
db.listingsAndReviews.find(twentyFour).count();







// Write a filter to return all documents where the first amenity listed is “Wifi”. 
const twentySix = {
    "amenities.0": "Wifi"
}
db.listingsAndReviews.find(twentySix);
// How many records are returned for question 27? 
db.listingsAndReviews.find(twentySix).count();






// Write  a filter to return all documents where one of the amenities begins with the letter ‘H”. 

// How many records are returned for question 29? 








// // Write a filter to return all documents.
// const thirty =
// {

// }
// db.listingsAndReviews.find(thirty);

// // How many records are returned for question 31?
// print(db.listingsAndReviews.find(thirty).count());







// Write a filter that returns all documents that have a market equal to: 
// “Hong Kong”, “New York”, “Porto”,  “Sydney” or “Istanbul” in the address field.
// const thirtyTwo = {
//   $or: [
//     { "address.market": "Hong Kong" },
//     { "address.market": "New York" },
//     { "address.market": "Porto" },
//     { "address.market": "Sydney" },
//     { "address.market": "Istanbul" }
//   ]
// };
// db.listingsAndReviews.find(thirtyTwo);

// // How many records are returned for question 33? 
// print(db.listingsAndReviews.find(thirtyTwo).count());