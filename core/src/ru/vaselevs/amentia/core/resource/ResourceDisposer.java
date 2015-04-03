package ru.vaselevs.amentia.core.resource;

import com.badlogic.gdx.utils.Disposable;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by CoreX on 23.03.2015.
 */
public class ResourceDisposer {
    private List<IDisposable> resources;
    private List<Disposable> gdxResources;

    public ResourceDisposer() {
        this.resources = new LinkedList<>();
        this.gdxResources = new LinkedList<>();
    }

    public void addResource(IDisposable resource) {
        this.resources.add(resource);
    }

    public void addResource(Disposable gdxResource) {
        this.gdxResources.add(gdxResource);
    }

    public void disposeAll() {
        this.resources.forEach(ru.vaselevs.amentia.core.resource.IDisposable::dispose);
        this.gdxResources.forEach(com.badlogic.gdx.utils.Disposable::dispose);
    }
}
