# Utility class that permits self-referencing ResourceBundles #

---

**Update:** Added code to get this working for the [Stripes Framework](http://www.stripesframework.org)

---

This was written to address a severe deficiency with Java [ResourceBundle](http://java.sun.com/j2se/1.4.2/docs/api/java/util/ResourceBundle.html)s: messages cannot reference other messages.  This directly, egregiously violates the DRY (don't repeat yourself) programming principle.

Example:
```
company.service.phone=555-1212
company.service.email=help@company.com
my.error.message=Problem accessing account!\
  Please call customer service at 555-1212 \
  or email us at help@company.com
```
The problem is quickly evident: if you have several messages that all reference the customer service email or phone number, and that value changes at any point, you now have to update hundreds of resource messages.

Solution:
```
company.service.phone=555-1212
company.service.email=help@company.com
my.error.message=Problem accessing account!\
  Please call customer service at ${company.service.phone} \
  or email us at ${company.service.email}
```
The advantages should be fairly obvious.