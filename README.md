SproutAPI (Alpha)
=========

Android library for Sprout ReST API.  Alpha release.  Still very much under development.

Example code:

SproutApi api = new SproutApi(SPROUT_TOKEN);

ArrayList<SproutCard> cards = api.getCards(phone, pin);

