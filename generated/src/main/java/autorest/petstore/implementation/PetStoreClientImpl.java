/**
 * Describe license header.
 *
 */

package autorest.petstore.implementation;

import autorest.petstore.PetStoreClient;
import com.microsoft.rest.ServiceClient;
import com.microsoft.rest.RestClient;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import autorest.petstore.models.ApiResponse;
import autorest.petstore.models.LoginUserHeaders;
import autorest.petstore.models.Order;
import autorest.petstore.models.Pet;
import autorest.petstore.models.User;
import com.google.common.reflect.TypeToken;
import com.microsoft.rest.CollectionFormat;
import com.microsoft.rest.RestException;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceFuture;
import com.microsoft.rest.ServiceResponse;
import com.microsoft.rest.ServiceResponseWithHeaders;
import com.microsoft.rest.Validator;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.Response;
import rx.functions.Func1;
import rx.Observable;

/**
 * Initializes a new instance of the PetStoreClient class.
 */
public class PetStoreClientImpl extends ServiceClient implements PetStoreClient {
    /**
     * The Retrofit service to perform REST calls.
     */
    private PetStoreClientService service;

    /**
     * Initializes an instance of PetStoreClient client.
     */
    public PetStoreClientImpl() {
        this("https://petstore.swagger.io/v2");
    }

    /**
     * Initializes an instance of PetStoreClient client.
     *
     * @param baseUrl the base URL of the host
     */
    public PetStoreClientImpl(String baseUrl) {
        super(baseUrl);
        initialize();
    }

    /**
     * Initializes an instance of PetStoreClient client.
     *
     * @param clientBuilder the builder for building an OkHttp client, bundled with user configurations
     * @param restBuilder the builder for building an Retrofit client, bundled with user configurations
     */
    public PetStoreClientImpl(OkHttpClient.Builder clientBuilder, Retrofit.Builder restBuilder) {
        this("https://petstore.swagger.io/v2", clientBuilder, restBuilder);
        initialize();
    }

    /**
     * Initializes an instance of PetStoreClient client.
     *
     * @param baseUrl the base URL of the host
     * @param clientBuilder the builder for building an OkHttp client, bundled with user configurations
     * @param restBuilder the builder for building an Retrofit client, bundled with user configurations
     */
    public PetStoreClientImpl(String baseUrl, OkHttpClient.Builder clientBuilder, Retrofit.Builder restBuilder) {
        super(baseUrl, clientBuilder, restBuilder);
        initialize();
    }

    /**
     * Initializes an instance of PetStoreClient client.
     *
     * @param restClient the REST client containing pre-configured settings
     */
    public PetStoreClientImpl(RestClient restClient) {
        super(restClient);
        initialize();
    }

    private void initialize() {
        initializeService();
    }

    private void initializeService() {
        service = retrofit().create(PetStoreClientService.class);
    }

    /**
     * The interface defining all the services for PetStoreClient to be
     * used by Retrofit to perform actually REST calls.
     */
    interface PetStoreClientService {
        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient addPet" })
        @POST("pet")
        Observable<Response<ResponseBody>> addPet(@Body Pet body);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient updatePet" })
        @PUT("pet")
        Observable<Response<ResponseBody>> updatePet(@Body Pet body);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient findPetsByStatus" })
        @GET("pet/findByStatus")
        Observable<Response<ResponseBody>> findPetsByStatus(@Query("status") String status);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient findPetsByTags" })
        @GET("pet/findByTags")
        Observable<Response<ResponseBody>> findPetsByTags(@Query("tags") String tags);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient getPetById" })
        @GET("pet/{petId}")
        Observable<Response<ResponseBody>> getPetById(@Path("petId") long petId);

        @Multipart
        @POST("pet/{petId}")
        Observable<Response<ResponseBody>> updatePetWithForm(@Path("petId") long petId, @Part("name") String name, @Part("status") String status);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient deletePet" })
        @HTTP(path = "pet/{petId}", method = "DELETE", hasBody = true)
        Observable<Response<ResponseBody>> deletePet(@Path("petId") long petId, @Header("api_key") String apiKey);

        @Multipart
        @POST("pet/{petId}/uploadImage")
        Observable<Response<ResponseBody>> uploadFile(@Path("petId") long petId, @Part("additionalMetadata") String additionalMetadata, @Part("file") RequestBody file);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient getInventory" })
        @GET("store/inventory")
        Observable<Response<ResponseBody>> getInventory();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient placeOrder" })
        @POST("store/order")
        Observable<Response<ResponseBody>> placeOrder(@Body Order body);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient getOrderById" })
        @GET("store/order/{orderId}")
        Observable<Response<ResponseBody>> getOrderById(@Path("orderId") long orderId);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient deleteOrder" })
        @HTTP(path = "store/order/{orderId}", method = "DELETE", hasBody = true)
        Observable<Response<ResponseBody>> deleteOrder(@Path("orderId") long orderId);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient createUser" })
        @POST("user")
        Observable<Response<ResponseBody>> createUser(@Body User body);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient createUsersWithArrayInput" })
        @POST("user/createWithArray")
        Observable<Response<ResponseBody>> createUsersWithArrayInput(@Body List<User> body);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient createUsersWithListInput" })
        @POST("user/createWithList")
        Observable<Response<ResponseBody>> createUsersWithListInput(@Body List<User> body);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient loginUser" })
        @GET("user/login")
        Observable<Response<ResponseBody>> loginUser(@Query("username") String username, @Query("password") String password);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient logoutUser" })
        @GET("user/logout")
        Observable<Response<ResponseBody>> logoutUser();

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient getUserByName" })
        @GET("user/{username}")
        Observable<Response<ResponseBody>> getUserByName(@Path("username") String username);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient updateUser" })
        @PUT("user/{username}")
        Observable<Response<ResponseBody>> updateUser(@Path("username") String username, @Body User body);

        @Headers({ "Content-Type: application/json; charset=utf-8", "x-ms-logging-context: autorest.petstore.PetStoreClient deleteUser" })
        @HTTP(path = "user/{username}", method = "DELETE", hasBody = true)
        Observable<Response<ResponseBody>> deleteUser(@Path("username") String username);

    }

    /**
     * Add a new pet to the store.
     *
     * @param body Pet object that needs to be added to the store
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void addPet(Pet body) {
        addPetWithServiceResponseAsync(body).toBlocking().single().body();
    }

    /**
     * Add a new pet to the store.
     *
     * @param body Pet object that needs to be added to the store
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> addPetAsync(Pet body, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(addPetWithServiceResponseAsync(body), serviceCallback);
    }

    /**
     * Add a new pet to the store.
     *
     * @param body Pet object that needs to be added to the store
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> addPetAsync(Pet body) {
        return addPetWithServiceResponseAsync(body).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     * Add a new pet to the store.
     *
     * @param body Pet object that needs to be added to the store
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> addPetWithServiceResponseAsync(Pet body) {
        if (body == null) {
            throw new IllegalArgumentException("Parameter body is required and cannot be null.");
        }
        Validator.validate(body);
        return service.addPet(body)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = addPetDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> addPetDelegate(Response<ResponseBody> response) throws RestException, IOException, IllegalArgumentException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .register(405, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     * Update an existing pet.
     *
     * @param body Pet object that needs to be added to the store
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void updatePet(Pet body) {
        updatePetWithServiceResponseAsync(body).toBlocking().single().body();
    }

    /**
     * Update an existing pet.
     *
     * @param body Pet object that needs to be added to the store
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> updatePetAsync(Pet body, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(updatePetWithServiceResponseAsync(body), serviceCallback);
    }

    /**
     * Update an existing pet.
     *
     * @param body Pet object that needs to be added to the store
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> updatePetAsync(Pet body) {
        return updatePetWithServiceResponseAsync(body).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     * Update an existing pet.
     *
     * @param body Pet object that needs to be added to the store
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> updatePetWithServiceResponseAsync(Pet body) {
        if (body == null) {
            throw new IllegalArgumentException("Parameter body is required and cannot be null.");
        }
        Validator.validate(body);
        return service.updatePet(body)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = updatePetDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> updatePetDelegate(Response<ResponseBody> response) throws RestException, IOException, IllegalArgumentException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .register(400, new TypeToken<Void>() { }.getType())
                .register(404, new TypeToken<Void>() { }.getType())
                .register(405, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     * Finds Pets by status.
     * Multiple status values can be provided with comma separated strings.
     *
     * @param status Status values that need to be considered for filter
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the List&lt;Pet&gt; object if successful.
     */
    public List<Pet> findPetsByStatus(List<String> status) {
        return findPetsByStatusWithServiceResponseAsync(status).toBlocking().single().body();
    }

    /**
     * Finds Pets by status.
     * Multiple status values can be provided with comma separated strings.
     *
     * @param status Status values that need to be considered for filter
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<List<Pet>> findPetsByStatusAsync(List<String> status, final ServiceCallback<List<Pet>> serviceCallback) {
        return ServiceFuture.fromResponse(findPetsByStatusWithServiceResponseAsync(status), serviceCallback);
    }

    /**
     * Finds Pets by status.
     * Multiple status values can be provided with comma separated strings.
     *
     * @param status Status values that need to be considered for filter
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;Pet&gt; object
     */
    public Observable<List<Pet>> findPetsByStatusAsync(List<String> status) {
        return findPetsByStatusWithServiceResponseAsync(status).map(new Func1<ServiceResponse<List<Pet>>, List<Pet>>() {
            @Override
            public List<Pet> call(ServiceResponse<List<Pet>> response) {
                return response.body();
            }
        });
    }

    /**
     * Finds Pets by status.
     * Multiple status values can be provided with comma separated strings.
     *
     * @param status Status values that need to be considered for filter
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;Pet&gt; object
     */
    public Observable<ServiceResponse<List<Pet>>> findPetsByStatusWithServiceResponseAsync(List<String> status) {
        if (status == null) {
            throw new IllegalArgumentException("Parameter status is required and cannot be null.");
        }
        Validator.validate(status);
        String statusConverted = this.serializerAdapter().serializeList(status, CollectionFormat.MULTI);
        return service.findPetsByStatus(statusConverted)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<List<Pet>>>>() {
                @Override
                public Observable<ServiceResponse<List<Pet>>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<List<Pet>> clientResponse = findPetsByStatusDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<List<Pet>> findPetsByStatusDelegate(Response<ResponseBody> response) throws RestException, IOException, IllegalArgumentException {
        return this.restClient().responseBuilderFactory().<List<Pet>, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<List<Pet>>() { }.getType())
                .register(400, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     * Finds Pets by tags.
     * Muliple tags can be provided with comma separated strings. Use         tag1, tag2, tag3 for testing.
     *
     * @param tags Tags to filter by
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the List&lt;Pet&gt; object if successful.
     */
    public List<Pet> findPetsByTags(List<String> tags) {
        return findPetsByTagsWithServiceResponseAsync(tags).toBlocking().single().body();
    }

    /**
     * Finds Pets by tags.
     * Muliple tags can be provided with comma separated strings. Use         tag1, tag2, tag3 for testing.
     *
     * @param tags Tags to filter by
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<List<Pet>> findPetsByTagsAsync(List<String> tags, final ServiceCallback<List<Pet>> serviceCallback) {
        return ServiceFuture.fromResponse(findPetsByTagsWithServiceResponseAsync(tags), serviceCallback);
    }

    /**
     * Finds Pets by tags.
     * Muliple tags can be provided with comma separated strings. Use         tag1, tag2, tag3 for testing.
     *
     * @param tags Tags to filter by
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;Pet&gt; object
     */
    public Observable<List<Pet>> findPetsByTagsAsync(List<String> tags) {
        return findPetsByTagsWithServiceResponseAsync(tags).map(new Func1<ServiceResponse<List<Pet>>, List<Pet>>() {
            @Override
            public List<Pet> call(ServiceResponse<List<Pet>> response) {
                return response.body();
            }
        });
    }

    /**
     * Finds Pets by tags.
     * Muliple tags can be provided with comma separated strings. Use         tag1, tag2, tag3 for testing.
     *
     * @param tags Tags to filter by
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;Pet&gt; object
     */
    public Observable<ServiceResponse<List<Pet>>> findPetsByTagsWithServiceResponseAsync(List<String> tags) {
        if (tags == null) {
            throw new IllegalArgumentException("Parameter tags is required and cannot be null.");
        }
        Validator.validate(tags);
        String tagsConverted = this.serializerAdapter().serializeList(tags, CollectionFormat.MULTI);
        return service.findPetsByTags(tagsConverted)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<List<Pet>>>>() {
                @Override
                public Observable<ServiceResponse<List<Pet>>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<List<Pet>> clientResponse = findPetsByTagsDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<List<Pet>> findPetsByTagsDelegate(Response<ResponseBody> response) throws RestException, IOException, IllegalArgumentException {
        return this.restClient().responseBuilderFactory().<List<Pet>, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<List<Pet>>() { }.getType())
                .register(400, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     * Find pet by ID.
     * Returns a single pet.
     *
     * @param petId ID of pet to return
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Pet object if successful.
     */
    public Pet getPetById(long petId) {
        return getPetByIdWithServiceResponseAsync(petId).toBlocking().single().body();
    }

    /**
     * Find pet by ID.
     * Returns a single pet.
     *
     * @param petId ID of pet to return
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Pet> getPetByIdAsync(long petId, final ServiceCallback<Pet> serviceCallback) {
        return ServiceFuture.fromResponse(getPetByIdWithServiceResponseAsync(petId), serviceCallback);
    }

    /**
     * Find pet by ID.
     * Returns a single pet.
     *
     * @param petId ID of pet to return
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Pet object
     */
    public Observable<Pet> getPetByIdAsync(long petId) {
        return getPetByIdWithServiceResponseAsync(petId).map(new Func1<ServiceResponse<Pet>, Pet>() {
            @Override
            public Pet call(ServiceResponse<Pet> response) {
                return response.body();
            }
        });
    }

    /**
     * Find pet by ID.
     * Returns a single pet.
     *
     * @param petId ID of pet to return
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Pet object
     */
    public Observable<ServiceResponse<Pet>> getPetByIdWithServiceResponseAsync(long petId) {
        return service.getPetById(petId)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Pet>>>() {
                @Override
                public Observable<ServiceResponse<Pet>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Pet> clientResponse = getPetByIdDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Pet> getPetByIdDelegate(Response<ResponseBody> response) throws RestException, IOException {
        return this.restClient().responseBuilderFactory().<Pet, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<Pet>() { }.getType())
                .register(400, new TypeToken<Void>() { }.getType())
                .register(404, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     * Updates a pet in the store with form data.
     *
     * @param petId ID of pet that needs to be updated
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void updatePetWithForm(long petId) {
        updatePetWithFormWithServiceResponseAsync(petId).toBlocking().single().body();
    }

    /**
     * Updates a pet in the store with form data.
     *
     * @param petId ID of pet that needs to be updated
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> updatePetWithFormAsync(long petId, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(updatePetWithFormWithServiceResponseAsync(petId), serviceCallback);
    }

    /**
     * Updates a pet in the store with form data.
     *
     * @param petId ID of pet that needs to be updated
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> updatePetWithFormAsync(long petId) {
        return updatePetWithFormWithServiceResponseAsync(petId).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     * Updates a pet in the store with form data.
     *
     * @param petId ID of pet that needs to be updated
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> updatePetWithFormWithServiceResponseAsync(long petId) {
        final String name = null;
        final String status = null;
        return service.updatePetWithForm(petId, name, status)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = updatePetWithFormDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    /**
     * Updates a pet in the store with form data.
     *
     * @param petId ID of pet that needs to be updated
     * @param name Updated name of the pet
     * @param status Updated status of the pet
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void updatePetWithForm(long petId, String name, String status) {
        updatePetWithFormWithServiceResponseAsync(petId, name, status).toBlocking().single().body();
    }

    /**
     * Updates a pet in the store with form data.
     *
     * @param petId ID of pet that needs to be updated
     * @param name Updated name of the pet
     * @param status Updated status of the pet
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> updatePetWithFormAsync(long petId, String name, String status, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(updatePetWithFormWithServiceResponseAsync(petId, name, status), serviceCallback);
    }

    /**
     * Updates a pet in the store with form data.
     *
     * @param petId ID of pet that needs to be updated
     * @param name Updated name of the pet
     * @param status Updated status of the pet
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> updatePetWithFormAsync(long petId, String name, String status) {
        return updatePetWithFormWithServiceResponseAsync(petId, name, status).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     * Updates a pet in the store with form data.
     *
     * @param petId ID of pet that needs to be updated
     * @param name Updated name of the pet
     * @param status Updated status of the pet
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> updatePetWithFormWithServiceResponseAsync(long petId, String name, String status) {
        return service.updatePetWithForm(petId, name, status)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = updatePetWithFormDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> updatePetWithFormDelegate(Response<ResponseBody> response) throws RestException, IOException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .register(405, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     * Deletes a pet.
     *
     * @param petId Pet id to delete
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void deletePet(long petId) {
        deletePetWithServiceResponseAsync(petId).toBlocking().single().body();
    }

    /**
     * Deletes a pet.
     *
     * @param petId Pet id to delete
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> deletePetAsync(long petId, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(deletePetWithServiceResponseAsync(petId), serviceCallback);
    }

    /**
     * Deletes a pet.
     *
     * @param petId Pet id to delete
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> deletePetAsync(long petId) {
        return deletePetWithServiceResponseAsync(petId).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     * Deletes a pet.
     *
     * @param petId Pet id to delete
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> deletePetWithServiceResponseAsync(long petId) {
        final String apiKey = null;
        return service.deletePet(petId, apiKey)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = deletePetDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    /**
     * Deletes a pet.
     *
     * @param petId Pet id to delete
     * @param apiKey the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void deletePet(long petId, String apiKey) {
        deletePetWithServiceResponseAsync(petId, apiKey).toBlocking().single().body();
    }

    /**
     * Deletes a pet.
     *
     * @param petId Pet id to delete
     * @param apiKey the String value
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> deletePetAsync(long petId, String apiKey, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(deletePetWithServiceResponseAsync(petId, apiKey), serviceCallback);
    }

    /**
     * Deletes a pet.
     *
     * @param petId Pet id to delete
     * @param apiKey the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> deletePetAsync(long petId, String apiKey) {
        return deletePetWithServiceResponseAsync(petId, apiKey).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     * Deletes a pet.
     *
     * @param petId Pet id to delete
     * @param apiKey the String value
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> deletePetWithServiceResponseAsync(long petId, String apiKey) {
        return service.deletePet(petId, apiKey)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = deletePetDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> deletePetDelegate(Response<ResponseBody> response) throws RestException, IOException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .register(400, new TypeToken<Void>() { }.getType())
                .register(404, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     * uploads an image.
     *
     * @param petId ID of pet to update
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the ApiResponse object if successful.
     */
    public ApiResponse uploadFile(long petId) {
        return uploadFileWithServiceResponseAsync(petId).toBlocking().single().body();
    }

    /**
     * uploads an image.
     *
     * @param petId ID of pet to update
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<ApiResponse> uploadFileAsync(long petId, final ServiceCallback<ApiResponse> serviceCallback) {
        return ServiceFuture.fromResponse(uploadFileWithServiceResponseAsync(petId), serviceCallback);
    }

    /**
     * uploads an image.
     *
     * @param petId ID of pet to update
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ApiResponse object
     */
    public Observable<ApiResponse> uploadFileAsync(long petId) {
        return uploadFileWithServiceResponseAsync(petId).map(new Func1<ServiceResponse<ApiResponse>, ApiResponse>() {
            @Override
            public ApiResponse call(ServiceResponse<ApiResponse> response) {
                return response.body();
            }
        });
    }

    /**
     * uploads an image.
     *
     * @param petId ID of pet to update
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ApiResponse object
     */
    public Observable<ServiceResponse<ApiResponse>> uploadFileWithServiceResponseAsync(long petId) {
        final String additionalMetadata = null;
        final byte[] file = new byte[0];
        RequestBody fileConverted = RequestBody.create(MediaType.parse("multipart/form-data"), new byte[0]);
        if (file != null) {
            fileConverted = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        }
        return service.uploadFile(petId, additionalMetadata, fileConverted)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<ApiResponse>>>() {
                @Override
                public Observable<ServiceResponse<ApiResponse>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<ApiResponse> clientResponse = uploadFileDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    /**
     * uploads an image.
     *
     * @param petId ID of pet to update
     * @param additionalMetadata Additional data to pass to server
     * @param file file to upload
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the ApiResponse object if successful.
     */
    public ApiResponse uploadFile(long petId, String additionalMetadata, byte[] file) {
        return uploadFileWithServiceResponseAsync(petId, additionalMetadata, file).toBlocking().single().body();
    }

    /**
     * uploads an image.
     *
     * @param petId ID of pet to update
     * @param additionalMetadata Additional data to pass to server
     * @param file file to upload
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<ApiResponse> uploadFileAsync(long petId, String additionalMetadata, byte[] file, final ServiceCallback<ApiResponse> serviceCallback) {
        return ServiceFuture.fromResponse(uploadFileWithServiceResponseAsync(petId, additionalMetadata, file), serviceCallback);
    }

    /**
     * uploads an image.
     *
     * @param petId ID of pet to update
     * @param additionalMetadata Additional data to pass to server
     * @param file file to upload
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ApiResponse object
     */
    public Observable<ApiResponse> uploadFileAsync(long petId, String additionalMetadata, byte[] file) {
        return uploadFileWithServiceResponseAsync(petId, additionalMetadata, file).map(new Func1<ServiceResponse<ApiResponse>, ApiResponse>() {
            @Override
            public ApiResponse call(ServiceResponse<ApiResponse> response) {
                return response.body();
            }
        });
    }

    /**
     * uploads an image.
     *
     * @param petId ID of pet to update
     * @param additionalMetadata Additional data to pass to server
     * @param file file to upload
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the ApiResponse object
     */
    public Observable<ServiceResponse<ApiResponse>> uploadFileWithServiceResponseAsync(long petId, String additionalMetadata, byte[] file) {
        RequestBody fileConverted = RequestBody.create(MediaType.parse("multipart/form-data"), new byte[0]);
        if (file != null) {
            fileConverted = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        }
        return service.uploadFile(petId, additionalMetadata, fileConverted)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<ApiResponse>>>() {
                @Override
                public Observable<ServiceResponse<ApiResponse>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<ApiResponse> clientResponse = uploadFileDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<ApiResponse> uploadFileDelegate(Response<ResponseBody> response) throws RestException, IOException {
        return this.restClient().responseBuilderFactory().<ApiResponse, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<ApiResponse>() { }.getType())
                .build(response);
    }

    /**
     * Returns pet inventories by status.
     * Returns a map of status codes to quantities.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Map&lt;String, Integer&gt; object if successful.
     */
    public Map<String, Integer> getInventory() {
        return getInventoryWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     * Returns pet inventories by status.
     * Returns a map of status codes to quantities.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Map<String, Integer>> getInventoryAsync(final ServiceCallback<Map<String, Integer>> serviceCallback) {
        return ServiceFuture.fromResponse(getInventoryWithServiceResponseAsync(), serviceCallback);
    }

    /**
     * Returns pet inventories by status.
     * Returns a map of status codes to quantities.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Map&lt;String, Integer&gt; object
     */
    public Observable<Map<String, Integer>> getInventoryAsync() {
        return getInventoryWithServiceResponseAsync().map(new Func1<ServiceResponse<Map<String, Integer>>, Map<String, Integer>>() {
            @Override
            public Map<String, Integer> call(ServiceResponse<Map<String, Integer>> response) {
                return response.body();
            }
        });
    }

    /**
     * Returns pet inventories by status.
     * Returns a map of status codes to quantities.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Map&lt;String, Integer&gt; object
     */
    public Observable<ServiceResponse<Map<String, Integer>>> getInventoryWithServiceResponseAsync() {
        return service.getInventory()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Map<String, Integer>>>>() {
                @Override
                public Observable<ServiceResponse<Map<String, Integer>>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Map<String, Integer>> clientResponse = getInventoryDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Map<String, Integer>> getInventoryDelegate(Response<ResponseBody> response) throws RestException, IOException {
        return this.restClient().responseBuilderFactory().<Map<String, Integer>, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<Map<String, Integer>>() { }.getType())
                .build(response);
    }

    /**
     * Place an order for a pet.
     *
     * @param body order placed for purchasing the pet
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Order object if successful.
     */
    public Order placeOrder(Order body) {
        return placeOrderWithServiceResponseAsync(body).toBlocking().single().body();
    }

    /**
     * Place an order for a pet.
     *
     * @param body order placed for purchasing the pet
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Order> placeOrderAsync(Order body, final ServiceCallback<Order> serviceCallback) {
        return ServiceFuture.fromResponse(placeOrderWithServiceResponseAsync(body), serviceCallback);
    }

    /**
     * Place an order for a pet.
     *
     * @param body order placed for purchasing the pet
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Order object
     */
    public Observable<Order> placeOrderAsync(Order body) {
        return placeOrderWithServiceResponseAsync(body).map(new Func1<ServiceResponse<Order>, Order>() {
            @Override
            public Order call(ServiceResponse<Order> response) {
                return response.body();
            }
        });
    }

    /**
     * Place an order for a pet.
     *
     * @param body order placed for purchasing the pet
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Order object
     */
    public Observable<ServiceResponse<Order>> placeOrderWithServiceResponseAsync(Order body) {
        if (body == null) {
            throw new IllegalArgumentException("Parameter body is required and cannot be null.");
        }
        Validator.validate(body);
        return service.placeOrder(body)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Order>>>() {
                @Override
                public Observable<ServiceResponse<Order>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Order> clientResponse = placeOrderDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Order> placeOrderDelegate(Response<ResponseBody> response) throws RestException, IOException, IllegalArgumentException {
        return this.restClient().responseBuilderFactory().<Order, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<Order>() { }.getType())
                .register(400, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     * Find purchase order by ID.
     * For valid response try integer IDs with value &gt;= 1 and &lt;= 10.         Other values will generated exceptions.
     *
     * @param orderId ID of pet that needs to be fetched
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the Order object if successful.
     */
    public Order getOrderById(long orderId) {
        return getOrderByIdWithServiceResponseAsync(orderId).toBlocking().single().body();
    }

    /**
     * Find purchase order by ID.
     * For valid response try integer IDs with value &gt;= 1 and &lt;= 10.         Other values will generated exceptions.
     *
     * @param orderId ID of pet that needs to be fetched
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Order> getOrderByIdAsync(long orderId, final ServiceCallback<Order> serviceCallback) {
        return ServiceFuture.fromResponse(getOrderByIdWithServiceResponseAsync(orderId), serviceCallback);
    }

    /**
     * Find purchase order by ID.
     * For valid response try integer IDs with value &gt;= 1 and &lt;= 10.         Other values will generated exceptions.
     *
     * @param orderId ID of pet that needs to be fetched
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Order object
     */
    public Observable<Order> getOrderByIdAsync(long orderId) {
        return getOrderByIdWithServiceResponseAsync(orderId).map(new Func1<ServiceResponse<Order>, Order>() {
            @Override
            public Order call(ServiceResponse<Order> response) {
                return response.body();
            }
        });
    }

    /**
     * Find purchase order by ID.
     * For valid response try integer IDs with value &gt;= 1 and &lt;= 10.         Other values will generated exceptions.
     *
     * @param orderId ID of pet that needs to be fetched
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the Order object
     */
    public Observable<ServiceResponse<Order>> getOrderByIdWithServiceResponseAsync(long orderId) {
        return service.getOrderById(orderId)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Order>>>() {
                @Override
                public Observable<ServiceResponse<Order>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Order> clientResponse = getOrderByIdDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Order> getOrderByIdDelegate(Response<ResponseBody> response) throws RestException, IOException {
        return this.restClient().responseBuilderFactory().<Order, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<Order>() { }.getType())
                .register(400, new TypeToken<Void>() { }.getType())
                .register(404, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     * Delete purchase order by ID.
     * For valid response try integer IDs with positive integer value.         Negative or non-integer values will generate API errors.
     *
     * @param orderId ID of the order that needs to be deleted
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void deleteOrder(long orderId) {
        deleteOrderWithServiceResponseAsync(orderId).toBlocking().single().body();
    }

    /**
     * Delete purchase order by ID.
     * For valid response try integer IDs with positive integer value.         Negative or non-integer values will generate API errors.
     *
     * @param orderId ID of the order that needs to be deleted
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> deleteOrderAsync(long orderId, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(deleteOrderWithServiceResponseAsync(orderId), serviceCallback);
    }

    /**
     * Delete purchase order by ID.
     * For valid response try integer IDs with positive integer value.         Negative or non-integer values will generate API errors.
     *
     * @param orderId ID of the order that needs to be deleted
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> deleteOrderAsync(long orderId) {
        return deleteOrderWithServiceResponseAsync(orderId).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     * Delete purchase order by ID.
     * For valid response try integer IDs with positive integer value.         Negative or non-integer values will generate API errors.
     *
     * @param orderId ID of the order that needs to be deleted
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> deleteOrderWithServiceResponseAsync(long orderId) {
        return service.deleteOrder(orderId)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = deleteOrderDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> deleteOrderDelegate(Response<ResponseBody> response) throws RestException, IOException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .register(400, new TypeToken<Void>() { }.getType())
                .register(404, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     * Create user.
     * This can only be done by the logged in user.
     *
     * @param body Created user object
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void createUser(User body) {
        createUserWithServiceResponseAsync(body).toBlocking().single().body();
    }

    /**
     * Create user.
     * This can only be done by the logged in user.
     *
     * @param body Created user object
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> createUserAsync(User body, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(createUserWithServiceResponseAsync(body), serviceCallback);
    }

    /**
     * Create user.
     * This can only be done by the logged in user.
     *
     * @param body Created user object
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> createUserAsync(User body) {
        return createUserWithServiceResponseAsync(body).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     * Create user.
     * This can only be done by the logged in user.
     *
     * @param body Created user object
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> createUserWithServiceResponseAsync(User body) {
        if (body == null) {
            throw new IllegalArgumentException("Parameter body is required and cannot be null.");
        }
        Validator.validate(body);
        return service.createUser(body)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = createUserDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> createUserDelegate(Response<ResponseBody> response) throws RestException, IOException, IllegalArgumentException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .build(response);
    }

    /**
     * Creates list of users with given input array.
     *
     * @param body List of user object
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void createUsersWithArrayInput(List<User> body) {
        createUsersWithArrayInputWithServiceResponseAsync(body).toBlocking().single().body();
    }

    /**
     * Creates list of users with given input array.
     *
     * @param body List of user object
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> createUsersWithArrayInputAsync(List<User> body, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(createUsersWithArrayInputWithServiceResponseAsync(body), serviceCallback);
    }

    /**
     * Creates list of users with given input array.
     *
     * @param body List of user object
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> createUsersWithArrayInputAsync(List<User> body) {
        return createUsersWithArrayInputWithServiceResponseAsync(body).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     * Creates list of users with given input array.
     *
     * @param body List of user object
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> createUsersWithArrayInputWithServiceResponseAsync(List<User> body) {
        if (body == null) {
            throw new IllegalArgumentException("Parameter body is required and cannot be null.");
        }
        Validator.validate(body);
        return service.createUsersWithArrayInput(body)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = createUsersWithArrayInputDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> createUsersWithArrayInputDelegate(Response<ResponseBody> response) throws RestException, IOException, IllegalArgumentException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .build(response);
    }

    /**
     * Creates list of users with given input array.
     *
     * @param body List of user object
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void createUsersWithListInput(List<User> body) {
        createUsersWithListInputWithServiceResponseAsync(body).toBlocking().single().body();
    }

    /**
     * Creates list of users with given input array.
     *
     * @param body List of user object
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> createUsersWithListInputAsync(List<User> body, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(createUsersWithListInputWithServiceResponseAsync(body), serviceCallback);
    }

    /**
     * Creates list of users with given input array.
     *
     * @param body List of user object
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> createUsersWithListInputAsync(List<User> body) {
        return createUsersWithListInputWithServiceResponseAsync(body).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     * Creates list of users with given input array.
     *
     * @param body List of user object
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> createUsersWithListInputWithServiceResponseAsync(List<User> body) {
        if (body == null) {
            throw new IllegalArgumentException("Parameter body is required and cannot be null.");
        }
        Validator.validate(body);
        return service.createUsersWithListInput(body)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = createUsersWithListInputDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> createUsersWithListInputDelegate(Response<ResponseBody> response) throws RestException, IOException, IllegalArgumentException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .build(response);
    }

    /**
     * Logs user into the system.
     *
     * @param username The user name for login
     * @param password The password for login in clear text
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the String object if successful.
     */
    public String loginUser(String username, String password) {
        return loginUserWithServiceResponseAsync(username, password).toBlocking().single().body();
    }

    /**
     * Logs user into the system.
     *
     * @param username The user name for login
     * @param password The password for login in clear text
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<String> loginUserAsync(String username, String password, final ServiceCallback<String> serviceCallback) {
        return ServiceFuture.fromHeaderResponse(loginUserWithServiceResponseAsync(username, password), serviceCallback);
    }

    /**
     * Logs user into the system.
     *
     * @param username The user name for login
     * @param password The password for login in clear text
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the String object
     */
    public Observable<String> loginUserAsync(String username, String password) {
        return loginUserWithServiceResponseAsync(username, password).map(new Func1<ServiceResponseWithHeaders<String, LoginUserHeaders>, String>() {
            @Override
            public String call(ServiceResponseWithHeaders<String, LoginUserHeaders> response) {
                return response.body();
            }
        });
    }

    /**
     * Logs user into the system.
     *
     * @param username The user name for login
     * @param password The password for login in clear text
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the String object
     */
    public Observable<ServiceResponseWithHeaders<String, LoginUserHeaders>> loginUserWithServiceResponseAsync(String username, String password) {
        if (username == null) {
            throw new IllegalArgumentException("Parameter username is required and cannot be null.");
        }
        if (password == null) {
            throw new IllegalArgumentException("Parameter password is required and cannot be null.");
        }
        return service.loginUser(username, password)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponseWithHeaders<String, LoginUserHeaders>>>() {
                @Override
                public Observable<ServiceResponseWithHeaders<String, LoginUserHeaders>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponseWithHeaders<String, LoginUserHeaders> clientResponse = loginUserDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponseWithHeaders<String, LoginUserHeaders> loginUserDelegate(Response<ResponseBody> response) throws RestException, IOException, IllegalArgumentException {
        return this.restClient().responseBuilderFactory().<String, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<String>() { }.getType())
                .register(400, new TypeToken<Void>() { }.getType())
                .buildWithHeaders(response, LoginUserHeaders.class);
    }

    /**
     * Logs out current logged in user session.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void logoutUser() {
        logoutUserWithServiceResponseAsync().toBlocking().single().body();
    }

    /**
     * Logs out current logged in user session.
     *
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> logoutUserAsync(final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(logoutUserWithServiceResponseAsync(), serviceCallback);
    }

    /**
     * Logs out current logged in user session.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> logoutUserAsync() {
        return logoutUserWithServiceResponseAsync().map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     * Logs out current logged in user session.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> logoutUserWithServiceResponseAsync() {
        return service.logoutUser()
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = logoutUserDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> logoutUserDelegate(Response<ResponseBody> response) throws RestException, IOException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .build(response);
    }

    /**
     * Get user by user name.
     *
     * @param username The name that needs to be fetched. Use user1 for testing.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the User object if successful.
     */
    public User getUserByName(String username) {
        return getUserByNameWithServiceResponseAsync(username).toBlocking().single().body();
    }

    /**
     * Get user by user name.
     *
     * @param username The name that needs to be fetched. Use user1 for testing.
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<User> getUserByNameAsync(String username, final ServiceCallback<User> serviceCallback) {
        return ServiceFuture.fromResponse(getUserByNameWithServiceResponseAsync(username), serviceCallback);
    }

    /**
     * Get user by user name.
     *
     * @param username The name that needs to be fetched. Use user1 for testing.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the User object
     */
    public Observable<User> getUserByNameAsync(String username) {
        return getUserByNameWithServiceResponseAsync(username).map(new Func1<ServiceResponse<User>, User>() {
            @Override
            public User call(ServiceResponse<User> response) {
                return response.body();
            }
        });
    }

    /**
     * Get user by user name.
     *
     * @param username The name that needs to be fetched. Use user1 for testing.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the User object
     */
    public Observable<ServiceResponse<User>> getUserByNameWithServiceResponseAsync(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Parameter username is required and cannot be null.");
        }
        return service.getUserByName(username)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<User>>>() {
                @Override
                public Observable<ServiceResponse<User>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<User> clientResponse = getUserByNameDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<User> getUserByNameDelegate(Response<ResponseBody> response) throws RestException, IOException, IllegalArgumentException {
        return this.restClient().responseBuilderFactory().<User, RestException>newInstance(this.serializerAdapter())
                .register(200, new TypeToken<User>() { }.getType())
                .register(400, new TypeToken<Void>() { }.getType())
                .register(404, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     * Updated user.
     * This can only be done by the logged in user.
     *
     * @param username name that need to be updated
     * @param body Updated user object
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void updateUser(String username, User body) {
        updateUserWithServiceResponseAsync(username, body).toBlocking().single().body();
    }

    /**
     * Updated user.
     * This can only be done by the logged in user.
     *
     * @param username name that need to be updated
     * @param body Updated user object
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> updateUserAsync(String username, User body, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(updateUserWithServiceResponseAsync(username, body), serviceCallback);
    }

    /**
     * Updated user.
     * This can only be done by the logged in user.
     *
     * @param username name that need to be updated
     * @param body Updated user object
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> updateUserAsync(String username, User body) {
        return updateUserWithServiceResponseAsync(username, body).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     * Updated user.
     * This can only be done by the logged in user.
     *
     * @param username name that need to be updated
     * @param body Updated user object
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> updateUserWithServiceResponseAsync(String username, User body) {
        if (username == null) {
            throw new IllegalArgumentException("Parameter username is required and cannot be null.");
        }
        if (body == null) {
            throw new IllegalArgumentException("Parameter body is required and cannot be null.");
        }
        Validator.validate(body);
        return service.updateUser(username, body)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = updateUserDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> updateUserDelegate(Response<ResponseBody> response) throws RestException, IOException, IllegalArgumentException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .register(400, new TypeToken<Void>() { }.getType())
                .register(404, new TypeToken<Void>() { }.getType())
                .build(response);
    }

    /**
     * Delete user.
     * This can only be done by the logged in user.
     *
     * @param username The name that needs to be deleted
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws RestException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     */
    public void deleteUser(String username) {
        deleteUserWithServiceResponseAsync(username).toBlocking().single().body();
    }

    /**
     * Delete user.
     * This can only be done by the logged in user.
     *
     * @param username The name that needs to be deleted
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceFuture} object
     */
    public ServiceFuture<Void> deleteUserAsync(String username, final ServiceCallback<Void> serviceCallback) {
        return ServiceFuture.fromResponse(deleteUserWithServiceResponseAsync(username), serviceCallback);
    }

    /**
     * Delete user.
     * This can only be done by the logged in user.
     *
     * @param username The name that needs to be deleted
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<Void> deleteUserAsync(String username) {
        return deleteUserWithServiceResponseAsync(username).map(new Func1<ServiceResponse<Void>, Void>() {
            @Override
            public Void call(ServiceResponse<Void> response) {
                return response.body();
            }
        });
    }

    /**
     * Delete user.
     * This can only be done by the logged in user.
     *
     * @param username The name that needs to be deleted
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the {@link ServiceResponse} object if successful.
     */
    public Observable<ServiceResponse<Void>> deleteUserWithServiceResponseAsync(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Parameter username is required and cannot be null.");
        }
        return service.deleteUser(username)
            .flatMap(new Func1<Response<ResponseBody>, Observable<ServiceResponse<Void>>>() {
                @Override
                public Observable<ServiceResponse<Void>> call(Response<ResponseBody> response) {
                    try {
                        ServiceResponse<Void> clientResponse = deleteUserDelegate(response);
                        return Observable.just(clientResponse);
                    } catch (Throwable t) {
                        return Observable.error(t);
                    }
                }
            });
    }

    private ServiceResponse<Void> deleteUserDelegate(Response<ResponseBody> response) throws RestException, IOException, IllegalArgumentException {
        return this.restClient().responseBuilderFactory().<Void, RestException>newInstance(this.serializerAdapter())
                .register(400, new TypeToken<Void>() { }.getType())
                .register(404, new TypeToken<Void>() { }.getType())
                .build(response);
    }

}
